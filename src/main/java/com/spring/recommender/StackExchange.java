package com.spring.recommender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
@Component
@EnableScheduling
public class StackExchange {	
	
	@Scheduled(fixedRate=1000*3600*24)
	public void GetPopularTags()
	{
		try {
			//Requesting User Data
			String url="https://api.stackexchange.com/2.2/tags?site=stackoverflow";
			URL obj = new URL(url);
			HttpsURLConnection con ;
			int responseCode;
			BufferedReader in ;
			StringBuffer response = new StringBuffer();
			con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			if(responseCode!=200)
			{
				return;
			}
			InputStream stream = con.getInputStream();
			if ("gzip".equals(con.getContentEncoding())) 
			{
				stream = new GZIPInputStream(stream);
			}
			in = new BufferedReader(new InputStreamReader( stream ));	
			response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String reader = response.toString();
			JSONObject jsonObject = new JSONObject(reader);
			//Display Tags on terminal
			JSONArray elements=jsonObject.getJSONArray("items"); 
			System.out.println("All Popular Tags:");
			// take each value from the json array separately
			for(int i=0;i<elements.length();i++) 
			{
				JSONObject innerObj = elements.getJSONObject(i);
				System.out.println("Tag Name "+ innerObj.get("name"));
			}
			// Inserting Data into DB
			BasicDBObject document = (BasicDBObject)com.mongodb.util.JSON.parse(jsonObject.toString());
			DBCollection collection = DBConnection.getDB().getCollection("tagsImported");
			collection.remove(new BasicDBObject());
			collection.insert(document);
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static ArrayList<String> getRemainingSkills(String token)
	{
		try
		{
			JSONObject json =LinkedinAuth.getData("https://api.linkedin.com/v1/people/~:(skills)?oauth2_access_token="+token+"&format=json");	
			JSONObject skill=json.getJSONObject("skills");
			JSONArray values=skill.getJSONArray("values");
			ArrayList<String> skills=new ArrayList<String>();
			for (int i=0;i<values.length();i++)
			{
				JSONObject it=values.getJSONObject(i);
				JSONObject it1=it.getJSONObject("skill");
				skills.add(it1.getString("name"));
			}
			DBCollection tagsImported=DBConnection.getDB().getCollection("tagsImported");
			DBCursor cur=tagsImported.find();
			ArrayList<String> tags=new ArrayList<String>();
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
			for(String sk:skills)
			{
				if(tags.contains(sk.toLowerCase()))
				{

					tags.remove(sk.toLowerCase());
				}
			}
			
			return tags;
			//Display tags
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
