package co.ecubix.utils.read_files;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadLocators {
	
	private JsonNode platformNode = null;
	private JsonNode pageNode = null;
	private JsonNode locatorNode = null;
	private JsonNode locatorsOnPage = null;

	private static String webpageName="web";

	private void readLocatorsJSONFIle(String pageName, String locatorName) {
		File pageLocatorJSONFile = new File(System.getProperty("user.dir")+"/src/test/resources/locators_repository/locators.json");
		try {
			String jsonString = FileUtils.readFileToString(pageLocatorJSONFile, StandardCharsets.UTF_8);
			JsonNode platformNode = new ObjectMapper().readTree(jsonString);
			setPlatformNode(platformNode);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected String getType(String pageName, String locatorName) {
		readLocatorsJSONFIle(pageName, locatorName);
		try {
			JsonNode platformNode = getPlatformNode().get(webpageName);
			JsonNode pageNode = platformNode.get(pageName);
			JsonNode locatorNode = pageNode.get(locatorName);
			JsonNode typeNode = locatorNode.get("type");
			return typeNode.asText();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected String getValue(String pageName, String locatorName) {
		readLocatorsJSONFIle(pageName, locatorName);
		try {
			JsonNode platformNode = getPlatformNode().get(webpageName);
			JsonNode pageNode = platformNode.get(pageName);
			JsonNode locatorNode = pageNode.get(locatorName);
			JsonNode valueNode = locatorNode.get("value");
			return valueNode.asText();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getWebpageName() {
		return webpageName;
	}

	public static void setWebpageName(String webpageName) {
		ReadLocators.webpageName = webpageName;
	}

	public JsonNode getPlatformNode() {
		return platformNode;
	}

	public void setPlatformNode(JsonNode platformNode) {
		this.platformNode = platformNode;
	}

	public JsonNode getPageNode() {
		return pageNode;
	}

	public void setPageNode(JsonNode pageNode) {
		this.pageNode = pageNode;
	}

	public JsonNode getLocatorNode() {
		return locatorNode;
	}

	public void setLocatorNode(JsonNode locatorNode) {
		this.locatorNode = locatorNode;
	}
	

	public JsonNode getLocatorsOnPage() {
		return locatorsOnPage;
	}

	public void setLocatorsOnPage(JsonNode locatorsOnPage) {
		this.locatorsOnPage = locatorsOnPage;
	}
	
}
