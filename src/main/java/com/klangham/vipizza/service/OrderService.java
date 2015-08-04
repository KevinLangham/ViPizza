/**
 * 
 */
package com.klangham.vipizza.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.klangham.vipizza.service.model.PizzaOrder;
import com.klangham.vipizza.util.JsonTransformer;
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
	private final DB db;
	private final DBCollection collection;

	public OrderService(DB db) {
		this.db = db;
		this.collection = db.getCollection("orders");
	}

	public List<PizzaOrder> findAllPizzaOrders() {
		List<PizzaOrder> orders = new ArrayList<>();
		DBCursor dbObjects = collection.find();
		while (dbObjects.hasNext()) {
			DBObject dbObject = dbObjects.next();
			orders.add(new PizzaOrder((BasicDBObject) dbObject));
		}
		return orders;
	}

	public void createNewPizzaOrder(String body) {
		PizzaOrder order = new Gson().fromJson(body, PizzaOrder.class);
		collection.insert(new BasicDBObject("baseSize", order.getBaseSize()).append("topping", order.getTopping())
				.append("price", order.getPrice()).append("createdOn", new Date()));
	}

	public PizzaOrder find(String id) {
		return new PizzaOrder((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
	}

}
