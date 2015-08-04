/**
 * 
 */
package com.klangham.vipizza;

import com.klangham.vipizza.service.OrderService;
import com.klangham.vipizza.util.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * @author klangham
 *
 */
public class Order {
	   private static final String API_CONTEXT = "/api/v1";
	   
	    private final OrderService orderService;
	 
	    public Order(OrderService orderService) {
	        this.orderService = orderService;
	        setupEndpoints();
	    }
	 
	    /**
    	 * Setup webservice endpoints.
    	 */
    	private void setupEndpoints() {
	        post(API_CONTEXT + "/order", "application/json", (request, response) -> {
	            orderService.createNewPizzaOrder(request.body());
	            response.status(201);
	            return response;
	        }, new JsonTransformer());
	 
	        get(API_CONTEXT + "/orders", "application/json", (request, response)
	 
	                -> orderService.findAllPizzaOrders(), new JsonTransformer());
	 	    }
	 
	 
}
