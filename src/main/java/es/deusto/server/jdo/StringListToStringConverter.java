package es.deusto.server.jdo;

import java.util.Arrays;
import java.util.List;

import javax.jdo.AttributeConverter;

public class StringListToStringConverter implements AttributeConverter<List<String>, String>{

	@Override
	public String convertToDatastore(List<String> attributeValue) {
		if (attributeValue == null) return null;
		return String.join(",", attributeValue);
	}

	@Override
	public List<String> convertToAttribute(String datastoreValue) {
		if (datastoreValue == null) return null;
		String[] values = datastoreValue.split(",");
		return Arrays.asList(values);
	}

}
