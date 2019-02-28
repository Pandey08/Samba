package hubble.samba.constants;

import hubble.samba.util.JsonFileParser;
import hubble.samba.util.YamlParser;

import java.util.HashMap;
import java.util.Map;

public class BuildActionsPageConstants {

	public static Map<String, String> BuildActionsPageLocators = new HashMap<String, String>();
	public static Map<String, HashMap<String,String>> buildDetailsMap = new HashMap<String, HashMap<String,String>>();


	public static Map<String, String> getBuildActionsPageLocators() {
		return BuildActionsPageLocators;
	}

	public static void setBuildActionsPageLocators(Map<String, String> buildActionsPageLocators) {
		BuildActionsPageLocators = buildActionsPageLocators;
	}

	public static void loadBuildActionsPageLocators() {
		setBuildActionsPageLocators(YamlParser.parseYamlFile("BuildActionsLocators.yaml"));
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
