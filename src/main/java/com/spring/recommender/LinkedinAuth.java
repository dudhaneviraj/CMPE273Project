package com.spring.recommender;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class LinkedinAuth {


    public static String getAccessToken(String code)throws Exception
    {
		URL obj = new URL("https://www.linkedin.com/uas/oauth2/accessToken");
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		//add reuqest header
		con.setRequestMethod("POST");
		String urlParameters = "grant_type=authorization_code&code="+code+"&redirect_uri=http://localhost:8080/recommender/callback&client_id=75gfml6z2nghgn&client_secret=nUrGxW0YyT9lXdz4";
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

}
