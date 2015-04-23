package demo;

import org.springframework.stereotype.Component;

@Component("foo")
public class ToUpperCaseTransformer implements Transformer {
	@Override
	public String transform(final String message) {
		return message.toUpperCase();
	}
}