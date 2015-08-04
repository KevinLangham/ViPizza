/**
 * 
 */
package com.klangham.vipizza.web;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

import com.klangham.vipizza.Order;
import com.klangham.vipizza.service.OrderService;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * @author klangham
 *
 */
public class Bootstrap {

	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
			? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null
			? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

	/**
	 * The main method.
	 *
	 * Initialise the application.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		setIpAddress(IP_ADDRESS);
		setPort(PORT);
		staticFileLocation("/public");
		new Order(new OrderService(mongo()));
	}

	/**
	 * Set up Mongo database connection.
	 *
	 * @return the db
	 * @throws Exception the exception
	 */
	private static DB mongo() throws Exception {
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		if (host == null) {
			MongoClient mongoClient = new MongoClient("localhost");
			return mongoClient.getDB("pizzaapp");
		}
		int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
		String dbname = System.getenv("OPENSHIFT_APP_NAME");
		String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		DB db = mongoClient.getDB(dbname);
		if (db.authenticate(username, password.toCharArray())) {
			return db;
		} else {
			throw new RuntimeException("Not able to authenticate with MongoDB");
		}
	}
}
