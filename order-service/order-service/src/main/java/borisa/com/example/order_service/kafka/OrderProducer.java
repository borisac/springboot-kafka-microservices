package borisa.com.example.order_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import borisa.com.example.base_domains.dto.OrderEvent;

@Service
public class OrderProducer {
	
	public static final Logger LOGGER= LoggerFactory.getLogger(OrderProducer.class);
	
	private NewTopic topic;
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event => %S", orderEvent.toString()));
		
		//create message
		Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		kafkaTemplate.send(message);
	}
}
