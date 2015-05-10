package com.spring.recommender;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DBConnection {
	
	public static DB getDB()
	{
		String textUri = "mongodb://viraj:12345@ds043981.mongolab.com:43981/cmpe273";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient mongo = new MongoClient(uri);
		DB db = mongo.getDB(uri.getDatabase());
		return db;
	}
}
