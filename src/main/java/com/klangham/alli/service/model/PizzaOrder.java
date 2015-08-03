/**
 * 
 */
package com.klangham.alli.service.model;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * @author klangham
 *
 */
public class PizzaOrder {
    private String id;
    private String baseSize;
    private String topping;
    private Double price;
    private Date createdOn = new Date();
 
    public PizzaOrder(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.baseSize = dbObject.getString("size");
        this.topping = dbObject.getString("topping");
        this.price = dbObject.getDouble("price");
        this.createdOn = dbObject.getDate("createdOn");
    }
 
    public String getBaseSize() {
		return baseSize;
	}

	public String getTopping() {
		return topping;
	}

	public Double getPrice() {
		return price;
	}

	public Date getCreatedOn() {
        return createdOn;
    }
}
