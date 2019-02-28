package hubble.samba.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.JsonObject;
import hubble.samba.util.DriverUtilities;
import hubble.samba.util.JsonFileParser;
import org.apache.log4j.Logger;

public class SambaProperties {

	public static Properties configDetails = new Properties();
    public static JsonObject deploymentSchedule = new JsonObject();
    public static JsonObject componentDependencies = new JsonObject();

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");


	private static String loginConfigPath = System.getProperty("user.dir") + FILE_SEPARATOR + "Properties" + FILE_SEPARATOR;
    private static String componentConfigPath = System.getProperty("user.dir") + FILE_SEPARATOR + "DeploymentConfigurations" + FILE_SEPARATOR;


	private static Logger logger = Logger.getLogger(DriverUtilities.class.getName());


	public void readAllConfigDetails(){
		
		logger.info("********** Reading login Config file *********** ");
		configDetails = readProperties(loginConfigPath + FILE_SEPARATOR + "suite.properties");
		logger.info("********** Reading login Config file - DONE ***********");

        logger.info("********** Reading Deployment Scehdule Config file *********** ");
        deploymentSchedule = JsonFileParser.parseJsonContent(componentConfigPath + FILE_SEPARATOR + "DeploymentSchedule.json");
        logger.info("********** Reading Deployment Scehdule Config file - DONE ***********");

        logger.info("********** Reading Component Dependencies Config file *********** ");
        componentDependencies = JsonFileParser.parseJsonContent(componentConfigPath + FILE_SEPARATOR + "ComponentDependencies.json");
        logger.info("********** Reading Component Dependencies Config file - DONE ***********");
    }


	private Properties readProperties(String filePath) {

        System.out.println("Properties File Path::"+filePath);

        File suiteProp= new File(filePath);
        FileReader fileReader;

        Properties props = new Properties();

        try {
            fileReader = new FileReader(suiteProp);
            props.load(fileReader);
        }
        catch (FileNotFoundException e1) {
            logger.info("!!!!!!!!!!!!!!!!! "+e1.getMessage());

            e1.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            logger.info("!!!!!!!!!!!!!!!!! "+e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

        return props;
    }

}
