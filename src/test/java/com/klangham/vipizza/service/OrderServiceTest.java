/**
 * 
 */
package com.klangham.vipizza.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.klangham.vipizza.service.model.PizzaOrder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author klangham
 *
 */
public class OrderServiceTest {

	private OrderService ordersService;
	private DBCollection dbCollection;

	@Before
	public void setUp() {
		DB db = mock(DB.class);
		dbCollection = mock(DBCollection.class);
		when(db.getCollection("orders")).thenReturn(dbCollection);

		ordersService = new OrderService(db);
	}
	
	/**
	 * Test method for {@link com.klangham.vipizza.service.OrderService#findAllPizzaOrders()}.
	 */
	@Test
	public void testFindAllPizzaOrders() {
		DBCursor dbCursor = mock(DBCursor.class);
		when(dbCollection.find()).thenReturn(dbCursor);
		
		ordersService.findAllPizzaOrders();
		verify(dbCollection).find();
	}

	/**
	 * Test method for {@link com.klangham.vipizza.service.OrderService#createNewPizzaOrder(java.lang.String)}.
	 */
	@Test
	public void testCreateNewPizzaOrder() {
		ordersService.createNewPizzaOrder("{'price':'8.99','baseSize':'Medium','topping':'Hawaiian'}");
		verify(dbCollection).insert((BasicDBObject) anyObject());
	}

}
