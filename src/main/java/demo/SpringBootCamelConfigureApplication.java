package demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringBootCamelConfigureApplication {
	
    public static void main(String[] args) {
    	SpringApplication.run(SpringBootCamelConfigureApplication.class, args);
    }
    
    @Bean
    public CamelContextConfiguration contextConfiguration() {
    	return new CamelContextConfiguration() {

			@Override
			public void beforeApplicationStart(CamelContext context) {
				final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

				// Note we can explicit name the component
				context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			}
    		
    	};
    }
    
    @Bean
    public RoutesBuilder myRouter() {
    	return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
	    		from("test-jms:queue:test.queue").beanRef("foo", "transform").to("file://test");
			}
    	};
    }

    @Bean
    public Transformer foo() {
    	return new Transformer() {
			
			@Override
			public String transform(final String message) {
				return message.toUpperCase();
			}
		};
    }
}
