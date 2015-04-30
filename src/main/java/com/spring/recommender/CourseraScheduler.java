package com.spring.recommender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Component
@EnableScheduling
public class CourseraScheduler {
	
		public static DB getDB()
		{
			String textUri = "mongodb://viraj:12345@ds043981.mongolab.com:43981/cmpe273";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo = new MongoClient(uri);
			DB db = mongo.getDB(uri.getDatabase());
			return db;
		}
		
		@Scheduled(fixedDelay = 1000)
		public void schedule()throws Exception
		{	
				DB db=getDB();
				DBCollection polls = db.getCollection("polls");

				
				
		}


}
