package borisa.com.example.email_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import borisa.com.example.base_domains.dto.OrderEvent;

@Service
public class OrderConsumer {

	public static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void conusme(OrderEvent event) {

		LOGGER.info(String.format("Order event received in stock service => %S", event.toString()));
		
		//here we can save order event data to db, but not now.just implementing communication
		
		

	}

}
