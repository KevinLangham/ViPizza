/**
 * 
 */
package com.klangham.vipizza.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.klangham.vipizza.service.model.PizzaOrder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author klangham
 *
 */
public class OrderService {
	@SuppressWarnings("unused")
	private final DB db;
	private final DBCollection collection;

	public OrderService(DB db) {
		this.db = db;
		this.collection = db.getCollection("orders");
	}

	/**
	 * Find all pizza orders.
	 *
	 * @return the list of pizza orders
	 */
	public List<PizzaOrder> findAllPizzaOrders() {
		List<PizzaOrder> orders = new ArrayList<>();
		DBCursor dbObjects = collection.find();
		while (dbObjects.hasNext()) {
			DBObject dbObject = dbObjects.next();
			orders.add(new PizzaOrder((BasicDBObject) dbObject));
		}
		return orders;
	}

	/**
	 * Creates the new pizza order.
	 *
	 * @param a JSON String representing the order
	 */
	public void createNewPizzaOrder(String body) {
		PizzaOrder order = new Gson().fromJson(body, PizzaOrder.class);
		collection.insert(new BasicDBObject("baseSize", order.getBaseSize()).append("topping", order.getTopping())
				.append("price", order.getPrice()).append("createdOn", new Date()));
	}

}
