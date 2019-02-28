package hubble.samba.util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonFileParser {
	
	public static JsonObject parseJsonContent(String jsonFilePath) {
		JsonObject jsonObject = loadJsonFile(jsonFilePath);
		return jsonObject;
	}

	private static JsonObject loadJsonFile(String jsonFile) {
		JsonObject jsonObj = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(jsonFile));
			JsonParser parser = new JsonParser();
			jsonObj = parser.parse(br).getAsJsonObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("JsonParserUtil : loadJsonFile :FileNotFoundException " + e.getMessage());

		}
		return jsonObj;
	}

	public static Map<String, HashMap<String,String>>  parseJsonFile(String fileName) {
		String configFileLocation =System.getProperty("user.dir") + "/ConfigurationFiles/"+fileName;
		JsonObject componentDetailsJson = null;
		componentDetailsJson=parseJsonContent(configFileLocation);
		System.out.println("Key:"+componentDetailsJson.keySet());

		//create Map out of the Json file parsed
		Map<String, HashMap<String,String>> componentDetailsMap = new  HashMap<String, HashMap<String,String>>();
		for (String componentKey : componentDetailsJson.keySet()) {
			HashMap<String,String> compDetailsMap = new Gson().fromJson(componentDetailsJson.get(componentKey), HashMap.class);
			componentDetailsMap.put(componentKey, compDetailsMap);
		}
		return componentDetailsMap;
	}
	
	public static void main(String args[]) {

		Boolean a= true;
		Boolean b= true;
		Boolean c= true;
		System.out.println(!a && !b);
//		String configFileLocation =System.getProperty("user.dir") + "/ConfigurationFiles/ComponentConfig.json";
//		JsonObject componentDetailsJson = null;
//		componentDetailsJson=parseJsonContent(configFileLocation);
//		System.out.println("A"+componentDetailsJson.keySet());
//
//		//create Map out of the Json file parsed
//		Map<String, HashMap<String,String>> componentDetailsMap = new  HashMap<String, HashMap<String,String>>();
//		for (String componentKey : componentDetailsJson.keySet()) {
//			HashMap<String,String> compDetailsMap = new Gson().fromJson(componentDetailsJson.get(componentKey), HashMap.class);
//			componentDetailsMap.put(componentKey, compDetailsMap);
//		}
//		System.out.println(componentDetailsMap.get("portlax").get("LANDING_PAGE"));
//		
//		LandingPageConstants.loadComponentDetaislMap();
//		System.out.println(LandingPageConstants.getComponentDetaislMap());
//		String landingPageSelections=LandingPageConstants.getComponentDetaislMap().get("portlax").get("LANDING_PAGE");
//		System.out.println("landingPageSelections::"+landingPageSelections);
	}
	

}
