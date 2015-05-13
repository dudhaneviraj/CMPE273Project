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

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.bcel.internal.generic.LNEG;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	static String fixedtoken="AQWAdZEPCcz57sHHFsi50UPmvC1x6tolgQ6UP5cUirRX4AgkLZ3AAkkBUgKoHo34PUXETsoqz1bnJjzhYxxu-OXVy2pTG6sOmGefxWCiIcPKIWF18p34JxVwDB7oQm6kcA9nxtkkYdNGRM79WWM-OALTGmrl5O5zP_VE0eT6pkwK9x_YArQ";
	static String linkedin_uri="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=75gfml6z2nghgn&redirect_uri=http://ec2-52-8-150-31.us-west-1.compute.amazonaws.com:8080/recommender/callback&state=DCEeFWf45A53se";
	//	static ModelAndView model=new ModelAndView("home").addObject("message", "Sorry! there Seems to be a Problem! Login Again!");
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);




	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView m=new ModelAndView("home");
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
	//Get Courses Based On linkedin  User Courses
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
		ModelAndView m=new ModelAndView("course");
		try 
		{	
			search=search.replaceAll(" ", "+");
			json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+search);
			System.out.println(json.toString());
			JSONArray js=json.getJSONArray("elements");
			for(int i=0;i<js.length();i++)
			{
				JSONObject it=js.getJSONObject(i);
				if(level==0 || level == 1)
				{
					if(it.has("targetAudience") && Integer.parseInt(it.getString("targetAudience"))<=1)
					{
						shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
						name.add(it.getString("name"));
						link.add(it.getString("largeIcon"));
						description.add(it.getString("shortDescription"));
						targetAudience.add(it.getString("targetAudience"));
					}	
				}
				else
				{
					if(it.has("targetAudience") && Integer.parseInt(it.getString("targetAudience"))<=1)
					{
						shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
						name.add(it.getString("name"));
						link.add(it.getString("largeIcon"));
						description.add(it.getString("shortDescription"));
						targetAudience.add(it.getString("targetAudience"));
					}	
				}
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
		//Requesting User Data

		String tempToken=token;
		JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(first-name,email-address)?oauth2_access_token="+token+"&format=json");	
		String email=json.getString("emailAddress");
		String name=json.getString("firstName");
		DBCollection tagsImported=DBConnection.getDB().getCollection("tagsImported");
		DBCursor cur=tagsImported.find();
		ArrayList<String> tags=new ArrayList<String>();
		ArrayList<String> count=new ArrayList<String>();
		BasicDBList list=new BasicDBList();
		while(cur.hasNext())
		{
			list=(BasicDBList)cur.next().get("items");
		}
		for(Object ob:list)
		{
			BasicDBObject b=(BasicDBObject)ob;
			tags.add(b.getString("name"));
		}

		String txt="Hi "+name+",<br>Here are the Recommended courses for You<br>";
		for(String tag:tags)
		{

			json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+tag);
			if(json!=null)
			{
				JSONArray js=json.getJSONArray("elements");
				if(js.length()!=0)
				{
					JSONObject it=js.getJSONObject(0);
					
					String url="https://www.coursera.org/course/"+it.getString("shortName");
					
					String courseName=it.getString("name");
					String icon=it.getString("largeIcon");
					txt=txt+"<img src='"+icon+"'><br>";
					txt=txt+"<a href='"+url+"'>"+courseName+"</a><br>";
					

				}
			}

		}
		EmailUser.mailUser(txt, email);
		json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(id,first-name,skills,educations,languages,twitter-accounts,headline,lastName)?oauth2_access_token="+tempToken+"&format=json");	
		//Getting data from JSON
		String firstName=json.getString("firstName");
		String headline=json.getString("headline");
		String lastName=json.getString("lastName");
		token=(String)DBConnection.getDB().getCollection("token").findOne().get("token");
		json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(id,first-name,skills,educations,languages,twitter-accounts,headline,lastName)?oauth2_access_token="+token+"&format=json");	
		//Getting data from JSON
		JSONObject skills=json.getJSONObject("skills");
		JSONArray values=skills.getJSONArray("values");
		String skill[]=new String[values.length()];
		JSONObject jsonpic=LinkedinAuth.getData("https://api.linkedin.com/v1/people/~/picture-urls::(original)?oauth2_access_token="+tempToken+"&format=json");
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

	//Get profile Data
	@RequestMapping(value="/{token}/trending")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView returnCourses(@PathVariable(value="token")String token)throws Exception
	{	

		ArrayList<String>shortName=new ArrayList<String>();
		ArrayList<String>name=new ArrayList<String>();
		ArrayList<String>link=new ArrayList<String>();
		ArrayList<String>description=new ArrayList<String>();
		ArrayList<String>targetAudience=new ArrayList<String>();


		try {
			ArrayList<String> trending=StackExchange.getRemainingSkills(token);
			ArrayList<JSONObject> jsonObject=new ArrayList<JSONObject>();

			for(int i=0;i<trending.size();i++)
			{
				String trend=trending.get(i);
				trending.set(i, trend);

				if(CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+trending.get(i))!=null)
				{
					JSONObject json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+trending.get(i));

					jsonObject.add(json);
				}
			}
			System.out.println("Length Of Json:"+jsonObject.size());
			for(JSONObject j:jsonObject)
			{

				if(j!=null)
				{
					JSONArray js=j.getJSONArray("elements");
					for(int i=0;i<js.length()&&i<5;i++)
					{
						JSONObject it=js.getJSONObject(i);
						if(it.has("targetAudience"))
						{
							shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
							name.add(it.getString("name"));
							link.add(it.getString("largeIcon"));
							description.add(it.getString("shortDescription"));
							targetAudience.add(it.getString("targetAudience"));
						}
					}
				}


			}


			System.out.println("length"+shortName.size());
			ModelAndView m=new ModelAndView("course");
			m.addObject("shortName", shortName);
			m.addObject("name", name);
			m.addObject("link", link);
			m.addObject("description", description);
			m.addObject("targetAudience",targetAudience);
			m.addObject("token", token);
			return m;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return null;
	}




	@RequestMapping(value="/{token}/menu")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView menu(@PathVariable(value="token")String token)throws Exception
	{

		return new ModelAndView("menu").addObject("token", token);
	}



	@RequestMapping(value="/{token}/contact")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView contact(@PathVariable(value="token")String token)throws Exception
	{
		return new ModelAndView("contact").addObject("token", token);
	}

	

	@RequestMapping(value="/{token}/about")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView about(@PathVariable(value="token")String token)throws Exception
	{
		return new ModelAndView("about").addObject("token", token);
	}

	
	
	
	
	
	
	
	@RequestMapping(value="/{token}/chart")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView chart(@PathVariable(value="token")String token)throws Exception
	{
		DBCollection tagsImported=DBConnection.getDB().getCollection("tagsImported");
		DBCursor cur=tagsImported.find();
		ArrayList<String> tags=new ArrayList<String>();
		ArrayList<String> count=new ArrayList<String>();
		BasicDBList list=new BasicDBList();
		while(cur.hasNext())
		{
			list=(BasicDBList)cur.next().get("items");
		}
		for(Object ob:list)
		{
			BasicDBObject b=(BasicDBObject)ob;
			tags.add(b.getString("name"));
			count.add(b.getString("count"));
		}
		ModelAndView m=new ModelAndView("charts");
		m.addObject("name", tags);
		m.addObject("count", count);
		return m;
	}
	//Get Courses Based On linkedin  User Courses
	@RequestMapping(value="/{token}/linkcourse")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView linkedinCourse(@PathVariable(value="token")String token)
	{		
		try {

			ModelAndView m=new ModelAndView("course");
			ArrayList<String>shortName=new ArrayList<String>();
			ArrayList<String>name=new ArrayList<String>();
			ArrayList<String>link=new ArrayList<String>();
			ArrayList<String>description=new ArrayList<String>();
			ArrayList<String>targetAudience=new ArrayList<String>();
			ArrayList<String> courseList=LinkedinAuth.getLinkedinCourses(token);
			for(String search:courseList)
			{
				search=search.replaceAll(" ", "+");
				JSONObject json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+search);
				JSONArray js=json.getJSONArray("elements");
				for(int i=0;i<js.length();i++)
				{
					JSONObject it=js.getJSONObject(i);
					shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
					name.add(it.getString("name"));
					link.add(it.getString("largeIcon"));
					description.add(it.getString("shortDescription"));
				}
			}
			m.addObject("shortName", shortName);
			m.addObject("name", name);
			m.addObject("link", link);
			m.addObject("description", description);
			m.addObject("targetAudience", targetAudience);
			m.addObject("token", token);
			return m;
		} catch (Exception e) {

			return new ModelAndView("home").addObject("message", "Sorry! Something Went Wrong!");
		}	
	}




	//Get Courses Based On linkedin  User Courses
	@RequestMapping(value="/{token}/trendingskillfromchart")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView chartSkill(@RequestParam(value="item")String search,@PathVariable(value="token")String token)
	{
		JSONObject json;
		int level=LinkedinAuth.getEducationLevel(token);
		System.out.println("level"+level);
		ArrayList<String>shortName=new ArrayList<String>();
		ArrayList<String>name=new ArrayList<String>();
		ArrayList<String>link=new ArrayList<String>();
		ArrayList<String>description=new ArrayList<String>();
		ArrayList<String>targetAudience=new ArrayList<String>();
		ModelAndView m=new ModelAndView("course");
		try 
		{	
			search=search.replaceAll(" ", "+");
			json=CourseEraFetcher.getData("https://api.coursera.org/api/catalog.v1/courses?fields=language,shortDescription,largeIcon,targetAudience&q=search&query="+search);
			System.out.println(json.toString());
			JSONArray js=json.getJSONArray("elements");
			for(int i=0;i<js.length();i++)
			{
				JSONObject it=js.getJSONObject(i);
				if(level==0 || level == 1)
				{
					if(it.has("targetAudience") && Integer.parseInt(it.getString("targetAudience"))<=1)
					{
						shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
						name.add(it.getString("name"));
						link.add(it.getString("largeIcon"));
						description.add(it.getString("shortDescription"));
						targetAudience.add(it.getString("targetAudience"));
					}	
				}
				else
				{
					if(it.has("targetAudience") && Integer.parseInt(it.getString("targetAudience"))<=1)
					{
						shortName.add("https://www.coursera.org/course/"+it.getString("shortName"));
						name.add(it.getString("name"));
						link.add(it.getString("largeIcon"));
						description.add(it.getString("shortDescription"));
						targetAudience.add(it.getString("targetAudience"));
					}	
				}
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


















}