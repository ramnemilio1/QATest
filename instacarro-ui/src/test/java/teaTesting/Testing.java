package teaTesting;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import base.PassionTeaBase;
import utils.GlobalUtils;

public class Testing {
	
	Logger logger;
	
	@Test
	public void tabNavigationTest(){
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to verify that each main tab "+
				"is working, in other words, it validates that after clicking a tab "+
				"user gets to the correct web page resource"+
				"\n ************************************************************** \n"
				);
		
		PassionTeaBase base = new PassionTeaBase(GlobalUtils.getDriver());
		base.navigatePassionTea();
		base.verifyAllTabs();
	}
	
	@Test
	public void WelcomeLinksTest(){
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to verify that each link "+
				"located at 'Welcome' page is re directing to the menu resource "+
				"\n ************************************************************** \n"
				);
		
		PassionTeaBase base = new PassionTeaBase(GlobalUtils.getDriver());
		base.navigatePassionTea();
		base.validateWelcomePageLinks();
	}
	
	@Test
	public void EmailFormTest(){
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to verify that an user "+
				"can type information in all fields for the 'Send us an email' section under 'Lets Talk Tea' tab "+
				"\n ************************************************************** \n"
				);
		PassionTeaBase base = new PassionTeaBase(GlobalUtils.getDriver());
		base.navigatePassionTea();
		base.fillEmailForm("Ramon", "tester@instacarro.com", "Selenium Test", "This is a test");
	}
	
	@After
	public void quit(){
		GlobalUtils.quitDriver();
	}
	
	@Rule 
	public TestRule classWatchman = new TestWatcher() {
		
	    @Override
	    protected void starting(Description desc) {    	
	    	logger = PassionTeaBase.getLogger();
			logger.info("\n ****************** "+ desc.getDisplayName()+" ****************** \n");
	    }

		@Override
		protected void succeeded(Description description) {
			logger.info("TEST PASSED! \n");
			super.succeeded(description);
		}

		@Override
		protected void failed(Throwable e, Description description) {
			logger.warning("TEST FAILED! \n");
			super.failed(e, description);
		}
	    
	    
	};

}
