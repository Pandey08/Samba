import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hubble.samba.model.SambaProperties;

import org.apache.log4j.Logger;

public class Test {

	private static Logger logger = Logger.getLogger(Test.class.getName());

	public static String CURRENT_HUBBLE_INSTALLATION = ""; //values can be Alpha/Beta/All



	public static void main(String [] args) {
		System.out.println("test inside main");
		try {

			Test test = new Test();

			// Read Properties
			test.readAllConfigDetails();

			//Set the current HUBBLE_INSTALLATION
			CURRENT_HUBBLE_INSTALLATION = SambaProperties.deploymentSchedule.get("HUBBLE_INSTALLATION").getAsString();
			System.out.println(CURRENT_HUBBLE_INSTALLATION);

			JsonArray componentDetailsArray = SambaProperties.deploymentSchedule.getAsJsonArray("COMPONENT_DETAILS");
			System.out.println(componentDetailsArray);
			
			
//			// Login
			test.doLogin();

			
			for(int i=0; i<componentDetailsArray.size(); i++) {
				test.installBuildComponent((JsonObject) componentDetailsArray.get(i));
				
				
			}
			
			test.restartServerForComponentDeployed();
			
			//Install
//			test.installBuildComponent();
//
//			//restart
////			test.restartServerForComponentDeployed();
			
			System.out.println("Execution Done");
		}catch (Exception e){
			System.out.println(e);
		}
	}



	public void readAllConfigDetails(){
		SambaProperties readAllProperties= new SambaProperties();
		readAllProperties.readAllConfigDetails();
	}


	public void doLogin(){
		logger.info("testLogin");
		System.out.println("testLogin");
		Login login = new Login();

		try {
			login.launchHubbleSamabaPage();
			login.hubbleSamabaPageLaunched();
			login.enterUsername(SambaProperties.configDetails.getProperty("USERNAME"));
			login.enterPassword(SambaProperties.configDetails.getProperty("PASSWORD"));
			login.clickLetMeIn();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void installBuildComponent(JsonObject componentDetails){
//		public void installBuildComponent(JsonObject componentDetails){
		InstallBuild installBuild = new InstallBuild();
		
		String deploymentComponent = componentDetails.get("COMPONENT_NAME").getAsString();
		String buildGroup  = componentDetails.get("BUILD_GROUP").getAsString();
		String buildVersion = componentDetails.get("BUILD_VERSION").getAsString();
		
		System.out.println(buildGroup);
		System.out.println(deploymentComponent);
		System.out.println(buildVersion);
		
		try {
			installBuild.validateLandingPageSamba();
			installBuild.selectAllHubble2();
			installBuild.selectBuildActionsComponentPage();
			installBuild.validateBuildActionsPageLaunched();
			installBuild.selectBuildGroup(buildGroup);
			installBuild.selectDeploymentComponent(deploymentComponent);
			installBuild.enterBuildVersion(buildVersion);

			installBuild.installBuild();
			installBuild.clickOkAlert();
			installBuild.clickHereInstallSubmitted();
			installBuild.validateBuildInstalled();
			
			installBuild.gotoHubbleEnvironment(CURRENT_HUBBLE_INSTALLATION);

			installBuild.selectBuildGroup(buildGroup);
			installBuild.selectDeploymentComponent(deploymentComponent);
			installBuild.selectInstalledBuildVersion(buildVersion);
			//installBuild.selectSetCurrent();
			installBuild.setCurrentBuildAndSubmit();


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void restartServerForComponentDeployed(){
		InstallBuild installBuild = new InstallBuild();
		System.out.println("restartServerForComponentDeployed");
		try {
			installBuild.selectComponentStatusPage();
			installBuild.enterComponentComponentStatusPage();
			installBuild.clickFetchComponentStatusPage();
			installBuild.selectRelatedComponentInstances();
			installBuild.clickRestart();
			installBuild.validateInstanceHappy();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
