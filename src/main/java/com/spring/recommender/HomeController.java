package com.spring.recommender;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	static String fixedtoken="AQWAdZEPCcz57sHHFsi50UPmvC1x6tolgQ6UP5cUirRX4AgkLZ3AAkkBUgKoHo34PUXETsoqz1bnJjzhYxxu-OXVy2pTG6sOmGefxWCiIcPKIWF18p34JxVwDB7oQm6kcA9nxtkkYdNGRM79WWM-OALTGmrl5O5zP_VE0eT6pkwK9x_YArQ";
	static String linkedin_uri="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=75gfml6z2nghgn&redirect_uri=http://localhost:8080/recommender/callback&state=DCEeFWf45A53sd";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView m=new ModelAndView("home1");
		m.addObject("linkedin_uri", linkedin_uri);
		return m;
	}
	@RequestMapping(value="/error")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView error()
	{
		return new ModelAndView("home");
	}


	@RequestMapping(value="/{token}/coursesearch")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView courseEraSearch(@RequestParam(value="search")String search,@PathVariable(value="token")String token)
	{
		JSONObject json;
		int level=LinkedinAuth.getEducationLevel(token);
		System.out.println("level"+level);
		ArrayList<String>shortName=new ArrayList<String>();
		ArrayList<String>name=new ArrayList<String>();
		ArrayList<String>link=new ArrayList<String>();
		ArrayList<String>description=new ArrayList<String>();
		ArrayList<String>targetAudience=new ArrayList<String>();
		ModelAndView m=new ModelAndView("CourseSearch");
		try 
		{	
			json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+search);
			System.out.println(json.toString());
			JSONArray js=json.getJSONArray("elements");
			for(int i=0;i<js.length();i++)
			{
				JSONObject it=js.getJSONObject(i);
				if(it.has("targetAudience"))
				{
					shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
					name.add(it.getString("name"));
					link.add(it.getString("largeIcon"));
					description.add(it.getString("shortDescription"));
				}	

				if(it.has("targetAudience"))
					targetAudience.add(it.getString("targetAudience"));
			}
			m.addObject("shortName", shortName);
			m.addObject("name", name);
			m.addObject("link", link);
			m.addObject("description", description);
			m.addObject("targetAudience", targetAudience);
			m.addObject("token", token);
			return m;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Execption"+e);
			return m;
		}
	}

	@RequestMapping(value="/callback")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView step2(@RequestParam(value="code",required=false)String code,@RequestParam(value="state", required=false)String state,@RequestParam(value="token", required=false)String token)throws Exception
	{		

		token=LinkedinAuth.getAccessToken(code);

		//Requesting User Data
		ModelAndView m=new ModelAndView(new RedirectView("profile"));
		m.addObject("token", token);
		return m;
	}
	//Get profile Data
	@RequestMapping(value="/profile")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView viewProfile(@RequestParam(value="token", required=true)String token)throws Exception
	{		
		
		StackExchange.getRemainingSkills(token);
		//Requesting User Data
		JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(id,first-name,skills,educations,languages,twitter-accounts,headline,lastName)?oauth2_access_token="+token+"&format=json");	
		//Getting data from JSON
		String firstName=json.getString("firstName");
		String headline=json.getString("headline");
		String lastName=json.getString("lastName");
		JSONObject skills=json.getJSONObject("skills");
		JSONArray values=skills.getJSONArray("values");
		String skill[]=new String[values.length()];
		JSONObject jsonpic=LinkedinAuth.getData("https://api.linkedin.com/v1/people/~/picture-urls::(original)?oauth2_access_token="+token+"&format=json");
		String picture=jsonpic.getJSONArray("values").get(0).toString();
		for (int i=0;i<values.length();i++)
		{
			JSONObject it=values.getJSONObject(i);
			JSONObject it1=it.getJSONObject("skill");
			skill[i]=it1.getString("name");;
		}
		String skillSet=skill[0];
		for(int i=1;i<skill.length;i++)
		{
			skillSet=skillSet+"  ||  " +skill[i];
		}
		ModelAndView m=new ModelAndView("profile");
		m.addObject("firstName", firstName);
		m.addObject("lastName", lastName);
		m.addObject("headline", headline);
		m.addObject("picture", picture);
		m.addObject("skillSet", skillSet);
		m.addObject("token", token);
		return m;
	}
}