import org.apache.log4j.Logger;
import org.junit.Assert;


import hubble.samba.factory.PageFactory;
import hubble.samba.pageobjects.LandingPage;
import hubble.samba.pageobjects.ComponentPage;
import hubble.samba.pageobjects.BuildActionsPage;




public class InstallBuild {

    PageFactory pageFactoryObj = new PageFactory();
    private LandingPage landingPage= pageFactoryObj.getLandingPageObject();
    private ComponentPage componentPage= pageFactoryObj.getComponentPageObject();
    private BuildActionsPage buildActionsPage= pageFactoryObj.getBuildActionsPageObject();
    private static Logger logger = Logger.getLogger(InstallBuild.class.getName());



    // Landing Page
    public void validateLandingPageSamba() throws InterruptedException{
        logger.info("******** Landing Page of Hubble Samba is loaded *******");
        Assert.assertTrue(landingPage.sambaLandingPageLaunched());
    }

    public void selectAllHubble2() throws InterruptedException{
        logger.info("******** Select alpha in hubble2 *******");
        landingPage.clickAllHubble2();
    }

//    public void selectOptionLandingPage() throws InterruptedException{
//        logger.info("******** Select the option in the landing page for the component to deploy *******");
//        landingPage.selectOptionLandingPage();
//    }



    //Components Page
    public void selectBuildActionsComponentPage() throws InterruptedException{
        logger.info("******** Select the build actions in the component page *******");
        componentPage.selectBuildActionsComponentPage();
    }

    public void selectComponentStatusPage() throws InterruptedException{
        logger.info("******** Select Component Status page from Component dropdown *******");
        componentPage.selectComponentStatusPage();
    }

    public void enterComponentComponentStatusPage() throws InterruptedException{
        logger.info("******** Enter component name in Component Status Page *******");
        componentPage.enterComponentComponentStatusPage();
    }

    public void clickFetchComponentStatusPage() throws InterruptedException{
        logger.info("******** Click Fetch button to see component details *******");
        componentPage.clickFetchComponentStatusPage();
    }

    public void selectRelatedComponentInstances() throws InterruptedException{
        logger.info("******** Select all the related components*******");
        componentPage.selectRelatedComponentInstances();
    }

    public void clickRestart() throws InterruptedException{
        logger.info("******** Click Restart *******");
        componentPage.clickRestartServerComponent();
    }
    
    public void validateInstanceHappy() throws InterruptedException{
        logger.info("********  validateInstanceHappy *******");
        Assert.assertTrue(componentPage.validateInstanceHappyRestart());

    }


    //Build Actions Page
    public void validateBuildActionsPageLaunched() throws InterruptedException{
        logger.info("******** Validate Build Actions Page is loaded *******");
        Assert.assertTrue(buildActionsPage.selectedPageBuildActions());
    }

    public void selectBuildGroup(String buildGroup) throws InterruptedException{
        logger.info("******** selectBuildGroup *******");
        buildActionsPage.selectBuildGroup(buildGroup);
    }

    public void selectDeploymentComponent(String deploymentComponent) throws InterruptedException{
        logger.info("******** Select the component to be deployed *******");
        buildActionsPage.selectDeploymentComponent(deploymentComponent);
    }

    public void enterBuildVersion(String buildVersion) throws InterruptedException{
        logger.info("******** Enter the build version *******");
        buildActionsPage.enterBuildVersion(buildVersion);
    }

    public void selectSetCurrent() throws InterruptedException{
        logger.info("******** Select set Current *******");
        buildActionsPage.setCurrentBuild();
    }

    public void installBuild() throws InterruptedException{
        logger.info("******** Install Build *******");
        buildActionsPage.installBuild();
    }

    public void clickOkAlert() throws InterruptedException{
        logger.info("******** clickOkAlert *******");
        buildActionsPage.clickOkAlertInstallBuild();
    }

    public void clickHereInstallSubmitted() throws InterruptedException{
        logger.info("******** clickHereInstallSubmitted *******");
        buildActionsPage.clickHereInstallRequestSubmitted();
    }

    public void validateBuildInstalled() throws InterruptedException{
        logger.info("******** validateBuildInstalled *******");
        Assert.assertTrue(buildActionsPage.validateBuildInstalledSucessfully());
    }
    
    public void gotoHubbleEnvironment(String hubbleEnvironment) throws InterruptedException{
        logger.info("******** gotoHubbleEnvironment *******");
        buildActionsPage.gotoHubbleEnvironment(hubbleEnvironment);
    }

    public void selectInstalledBuildVersion(String buildVersion) throws InterruptedException{
        logger.info("******** Enter selectInstalledBuildVersion *******");
        buildActionsPage.selectInstalledBuildVersion(buildVersion);
    }

    public void setCurrentBuildAndSubmit() throws InterruptedException{
        logger.info("******** Select setCurrentBuildAndSubmit *******");
        buildActionsPage.setCurrentBuildAndSubmit();
    }
    
    
}
