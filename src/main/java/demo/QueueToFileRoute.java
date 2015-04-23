package demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueueToFileRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("test-jms:queue:test.queue").beanRef("foo", "transform").to("file://test");
	}
}