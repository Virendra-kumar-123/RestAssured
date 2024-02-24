package dynamicJSONPayloadsWithParameterization;

public class TestDataJSON {
	public static String getJSONData(String name, String accuracy) {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": "+accuracy+",\r\n"
				+ "  \"name\": \""+name+"\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"110, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://virendra.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
}
