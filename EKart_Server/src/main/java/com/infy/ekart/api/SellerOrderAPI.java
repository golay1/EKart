package com.infy.ekart.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.ekart.model.Order;
import com.infy.ekart.model.OrderStatus;
import com.infy.ekart.service.SellerOrderService;
@CrossOrigin
@RestController
@RequestMapping("SellerOrderAPI")
public class SellerOrderAPI {

	@Autowired
	private SellerOrderService sellerOrderService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(SellerAPI.class.getName());
	
	@PostMapping(value = "viewOrders")
	public ResponseEntity<?> viewOrders(@RequestBody String sellerEmailId) throws Exception {
		try
		{
			logger.info("FETCHING ALL ORDERS MADE FOR PRODUCTS SOLD BY SELLER : "+sellerEmailId);
			
			List<Order> orders = sellerOrderService.viewOrders(sellerEmailId);
			
			return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "updateOrderStatus/{orderId}/{orderStatus}")
	public ResponseEntity<String> updateOrderStatus(@PathVariable("orderId") Integer orderId, @PathVariable("orderStatus") String orderStatus) throws Exception {
		try
		{
			sellerOrderService.modifyOrderStatus(orderId, orderStatus);
			
			String modificationSuccessMsg = environment.getProperty("SellerOrderAPI.ORDER_STATUS_UPDATE_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	@GetMapping(value = "getAllOrderStatus")
	public ResponseEntity<List<String>> getAllOrderStatus() {
		try{
			
		
			List<String> orderStatusList = new ArrayList<>();
			
			OrderStatus []orderStatus = OrderStatus.values();
			for (OrderStatus orderStatus2 : orderStatus) {
				orderStatusList.add(orderStatus2.toString());
			}
			
			return new ResponseEntity<List<String>>(orderStatusList, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
