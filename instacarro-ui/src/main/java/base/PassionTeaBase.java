package base;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import pageObjects.PassionTeaPO;
import utils.GlobalUtils;

public class PassionTeaBase {
	private final String URL = "http://www.practiceselenium.com/";
	private  WebDriver driver;
	private PassionTeaPO pageObjects;
	private static Logger logger = Logger.getLogger("myLogger");
	
	public PassionTeaBase(WebDriver driver){
		this.driver = driver;
		pageObjects = new PassionTeaPO(driver);
	}
	
	
	
	public void navigatePassionTea(){
		logger.info("Opening >> "+URL);
		driver.get(URL);
	}
	
	public void verifyAllTabs(){
		logger.info("Validating Menu Tabs");
		// Tab #1 - Welcome
		navigateToTab("Welcome");
		validateTabInfo("Welcome");
		logger.info("Welcome Tab Validated");
		
		// Tab #2 - Our Passion
		navigateToTab("Our Passion");
		validateTabInfo("Our Passion");
		logger.info("Our Passion Tab Validated");
		
		// Tab #3 - Menu
		navigateToTab("Menu");
		validateTabInfo("Menu");
		logger.info("Menu Tab Validated");
		
		// Tab #4 - Let's Talk Tea
		navigateToTab("Talk Tea");
		validateTabInfo("Talk Tea");
		logger.info("Let's Talk Tea Tab Validated");
		
		// Tab #5 - Check Out
		navigateToTab("Check Out");
		validateTabInfo("Check Out");	
		logger.info("Check Out Tab Validated");
	}
	

	public void navigateToTab(String tab){
		pageObjects.getTab(tab).click();
	}
	
	public void validateTabInfo(String tab){
		
		// Check URL
		String currentURL= driver.getCurrentUrl();
		if(GlobalUtils.containedInUrl(currentURL, tab)){
			logger.info("Url resource's path verified");
		}
		
		else{
			logger.warning("Error >> Unexpected Url path for tab: "+ tab);
			throw new RuntimeException("Url Validation failed >> TAB: "+tab);
		}
			
		//Check Page content
		if(!(tab.contains("Welcome") || tab.contains("Check"))){
			 if(pageObjects.getHeaderLabel(tab) !=null){
				 logger.info("Page header verified");
			 }
			 else{
				 logger.warning("Error >> Unable to validate Page Header for tab: "+ tab);
			 }
			
		}
	}
	
	public void validateWelcomePageLinks(){
		logger.info("Validating Collection 'Herbal Tea' link");
		navigateToTab("Welcome");
		GlobalUtils.scrollToElement(pageObjects.getCollectionHerbalTea());
		pageObjects.getCollectionHerbalTea().click();
		validateTabInfo("Menu");
		
		logger.info("Validating Collection 'Loose Tea' link");
		navigateToTab("Welcome");
		GlobalUtils.scrollToElement(pageObjects.getCollectionLooseTea());
		pageObjects.getCollectionLooseTea().click();
		validateTabInfo("Menu");
		
		logger.info("Validating Collection 'Flavored Tea' link");
		navigateToTab("Welcome");
		GlobalUtils.scrollToElement(pageObjects.getCollectionFlavoredTea());
		pageObjects.getCollectionFlavoredTea().click();
		validateTabInfo("Menu");	
	}
	
	public void fillEmailForm(String name, String email, String subject, String msg){
		logger.info("Filling Email Form");
		navigateToTab("Talk Tea");
		pageObjects.getNameField().clear();
		pageObjects.getNameField().sendKeys(name);
		
		pageObjects.getEmailField().clear();
		pageObjects.getEmailField().sendKeys(email);
		
		GlobalUtils.scrollToElement(pageObjects.getSubjectField());
		pageObjects.getSubjectField().clear();
		pageObjects.getSubjectField().sendKeys(subject);
		
		pageObjects.getMessageField().clear();
		pageObjects.getMessageField().sendKeys(msg);
		
		pageObjects.getSubmitButton().click();
	}
	
	public static Logger getLogger(){
		return logger;
	}

}
