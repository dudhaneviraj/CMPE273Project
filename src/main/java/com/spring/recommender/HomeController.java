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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	static String fixedtoken="AQWAdZEPCcz57sHHFsi50UPmvC1x6tolgQ6UP5cUirRX4AgkLZ3AAkkBUgKoHo34PUXETsoqz1bnJjzhYxxu-OXVy2pTG6sOmGefxWCiIcPKIWF18p34JxVwDB7oQm6kcA9nxtkkYdNGRM79WWM-OALTGmrl5O5zP_VE0eT6pkwK9x_YArQ";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
    @RequestMapping(value="/callback")
    @ResponseBody
    @ResponseStatus(value=HttpStatus.OK)
    public ModelAndView step2(@RequestParam(value="code",required=false)String code,@RequestParam(value="state")String state)throws Exception
    {		
    
    		//Requesting Access Token
    		String token=LinkedinAuth.getAccessToken(code);
    		
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
    				String sk=it1.getString("name");
    				skill[i]=sk;
    				
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
    
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
//    			JSONObject companies=LinkedinAuth.getData("https://api.linkedin.com/v1/people/~/following/companies?oauth2_access_token="+token+"&format=json");
//    	    	JSONArray cmp=companies.getJSONArray("values");
//    	    	
//    	    	//Get Company Ids
//    	    	String[] companyId=new String[cmp.length()];
//    	    	for(int i=0;i<cmp.length();i++)
//    	    	{
//    	    		companyId[i]=cmp.getJSONObject(i).getInt("id")+"";
//    	    	}
//    	    	
//    	    	//get Job Ids
//    	    	ArrayList<String> jobId=new ArrayList<String>();
//    	    	for(String temp:companyId)
//    	    	{
//    	    		JSONObject companyJobs=LinkedinAuth.getData("https://api.linkedin.com/v1/companies/"+temp+"/updates?oauth2_access_token="+token+"&event-type=job-posting&format=json"); 
//    	    	
//    	    		if(!companyJobs.has("values"))
//    	    		{	
//    	    	 		System.out.println("Company id without jobs:"+temp);
//    	    			continue;
//    	    		}
//    	    		JSONArray val=companyJobs.getJSONArray("values");
//    	    		for(int i=0;i<val.length();i++)
//    	    		{ 			
//    	    			jobId.add(val.getJSONObject(i).getJSONObject("updateContent").getJSONObject("companyJobUpdate").getJSONObject("job").getInt("id")+"");
//    	    		}
//    	    		
//    	    	
//    	    	}
//    	    	
//    	    	for(String job:jobId)
//    	    	{
//    	    		JSONObject tempJob=LinkedinAuth.getData("https://api.linkedin.com/v1/jobs/"+job+":(id,company:(id,name),position:(title,location,job-functions,job-type,experience-level),skills-and-experience,description,location-description)?oauth2_access_token="+fixedtoken+"&format=json");
//    	    		
//    	    		System.out.println(tempJob.toString());
//    	    	}
//    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			return m;
      
    
    
    
    
    
    }

	
	
	
	
	
}
