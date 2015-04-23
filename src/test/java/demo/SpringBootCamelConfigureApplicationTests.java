package demo;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootCamelConfigureApplication.class)
public class SpringBootCamelConfigureApplicationTests {

	@Autowired
	private CamelContext camelContext;
	
	@Autowired
	private ProducerTemplate producerTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void fireSomeObjectsIntoCamel() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			producerTemplate.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
		}
		
		Thread.sleep(10000);
	}

}
