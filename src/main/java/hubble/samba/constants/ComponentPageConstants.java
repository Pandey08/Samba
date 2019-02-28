package hubble.samba.constants;

import hubble.samba.util.JsonFileParser;
import hubble.samba.util.YamlParser;

import java.util.HashMap;
import java.util.Map;

public class ComponentPageConstants {

	public static Map<String, String> ComponentPageLocators = new HashMap<String, String>();
	public static Map<String, HashMap<String,String>> buildDetailsMap = new HashMap<String, HashMap<String,String>>();

	public static Map<String, String> getComponentPageLocators() {
		return ComponentPageLocators;
	}
	
	public static void setComponentPageLocators(Map<String, String> componentPageLocators) {
		ComponentPageLocators = componentPageLocators;
	}
	
	public static void loadComponentPageLocators() {
		setComponentPageLocators(YamlParser.parseYamlFile("ComponentLocators.yaml"));
	}
	
//	public static Map<String, HashMap<String,String>> getBuildDetailsMap() {
//		return buildDetailsMap;
//	}
//
//	public static void setBuildDetailsMap(Map<String, HashMap<String,String>> buildDetails) {
//		buildDetailsMap = buildDetails;
//	}
//
//	public static void loadBuildDetailsMap() {
//		setBuildDetailsMap(JsonFileParser.parseJsonFile("Input.json"));
//	}
}
