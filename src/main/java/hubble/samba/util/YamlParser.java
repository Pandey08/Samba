package hubble.samba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlParser {

	public static Map<String, String> parseYamlFile(String fileName) {

		Yaml yml = new Yaml();
		Map<String, String> yamlParsedValue = new HashMap<>();

		//generate the filePath
		String filePath = System.getProperty("user.dir") + "/ElementLocators/"+fileName;
		File inputFile = new File(filePath);

		try {
			FileInputStream file = new FileInputStream(inputFile);
			System.out.println("File output" +file);
			yamlParsedValue = (HashMap<String, String>) yml.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return yamlParsedValue;
	}

	public static void main(String args[]) {
		YamlParser yp = new YamlParser();
		//yp.parseYamlFile("test.yaml");
	}
}
