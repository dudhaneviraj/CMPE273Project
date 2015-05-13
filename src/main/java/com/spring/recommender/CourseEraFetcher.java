package com.spring.recommender;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


@Controller
public class CourseEraFetcher {

	@RequestMapping("/Courses/Company")
	public ModelAndView greet(@RequestParam(value="token")String token)throws Exception
	{
		//Get Companies followed by user
		JSONObject companies=getData("https://api.linkedin.com/v1/people/~/following/companies?oauth2_access_token="+token+"&format=json");
		JSONArray cmp=companies.getJSONArray("values");

		//Get Company Ids
		String[] companyId=new String[cmp.length()];
		for(int i=0;i<cmp.length();i++)
		{
			companyId[i]=cmp.getJSONObject(i).getInt("id")+"";
		}

		//get Job Ids
		ArrayList<String> jobId=new ArrayList<String>();
		for(String temp:companyId)
		{
			JSONObject companyJobs=getData("https://api.linkedin.com/v1/companies/"+temp+"/updates?oauth2_access_token="+token+"&event-type=job-posting&format=json"); 
			JSONArray values=companyJobs.getJSONArray("values");
			for(int i=0;i<values.length();i++)
			{ 			
				jobId.add(values.getJSONObject(i).getJSONObject("updateContent").getJSONObject("companyJobUpdate").getJSONObject("job").getInt("id")+"");
			}

			for(String ss:jobId)
			{
				System.out.println("JobIds"+ss);
			}
		}
		ModelAndView m=new ModelAndView("/pages/index");
		return m;
	}
	public static JSONObject getData(String url)
	{	
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();	
			return new JSONObject(response.toString());


		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
}
