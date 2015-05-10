package com.spring.recommender;
import org.json.JSONArray;
import org.json.JSONObject;

public class EducationLevel {

	public static int getEducationLevel(String token)
	{
		try {
			JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(id,first-name,skills,educations,languages,twitter-accounts,headline,lastName)?oauth2_access_token="+token+"&format=json");	
			JSONArray values=json.getJSONArray("values");
			int level=0;
			for(int i=0;i<values.length();i++)
			{

				String degree=values.getJSONObject(0).getString("degree");

				if(degree.toLowerCase().startsWith("d") || degree.toLowerCase().startsWith("m"))
				{

					return 2;
				}	

				if(degree.toLowerCase().startsWith("a") )
				{
					level=0;
				}
				if(degree.toLowerCase().startsWith("b") )
				{
					level=1;
				}	
			}
			return level;

		} catch (Exception e) {
			// TODO: handle exception
		}	
		return 0;
	}
}
