package demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.stereotype.Component;

@Component
public class MyCamelContextConfiguration implements CamelContextConfiguration {
	@Override
	public void beforeApplicationStart(CamelContext context) {
		final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

		// Note we can explicit name the component
		context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
	}
}