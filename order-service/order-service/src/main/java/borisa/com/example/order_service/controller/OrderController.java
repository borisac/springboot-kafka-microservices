package borisa.com.example.order_service.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borisa.com.example.base_domains.dto.Order;
import borisa.com.example.base_domains.dto.OrderEvent;
import borisa.com.example.order_service.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		super();
		this.orderProducer = orderProducer;
	}
	
	@PostMapping("/orders")
	public  String placeOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent =new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("order status is in pending state");
		orderEvent.setOrder(order);
		
		orderProducer.sendMessage(orderEvent);
		
		return "Order placed suscessfully ...";
	}
}
