package com.spring.recommender;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class LinkedinAuth {
	//linkedin access token
	public static String getAccessToken(String code)throws Exception
	{
		URL obj = new URL("https://www.linkedin.com/uas/oauth2/accessToken");
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		//add reuqest header
		con.setRequestMethod("POST");
		String urlParameters = "grant_type=authorization_code&code="+code+"&redirect_uri=http://ec2-52-8-150-31.us-west-1.compute.amazonaws.com:8080/recommender/callback&client_id=75gfml6z2nghgn&client_secret=nUrGxW0YyT9lXdz4";
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JSONObject js = new JSONObject(response.toString());
		return js.getString("access_token");
	}
	//Get Lenkedin Data
	public static JSONObject getData(String url)throws Exception
	{	
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
	}

	//Fetch Users Education Level
	public static int getEducationLevel(String token)
	{
		try {
			JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(educations)?oauth2_access_token="+token+"&format=json");	
			System.out.println("the String"+json.toString());
			JSONObject educations=json.getJSONObject("educations");
			JSONArray values=educations.getJSONArray("values");
			int level=0;
			for(int i=0;i<values.length();i++)
			{
				String degree=values.getJSONObject(0).getString("degree");
				System.out.println("degree:"+degree);
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
			
			System.out.println("The Exception is:"+e);
		}	
		return 0;
	}
	public static ArrayList<String> getLinkedinCourses(String token)
	{
		ArrayList<String> courseList=new ArrayList<String>();
		try {
			JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(courses)?oauth2_access_token="+token+"&format=json");	
			JSONObject courses=json.getJSONObject("courses");
			JSONArray values=courses.getJSONArray("values");
			for(int i=0;i<values.length();i++)
			{
				JSONObject temp=values.getJSONObject(i);
				courseList.add(temp.getString("name"));
			}
			return courseList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
