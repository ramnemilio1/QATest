package apiTesting;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import instacarro.API;
import instacarro.Genderize;

public class Testing {
	Logger logger;
	Genderize gen;
	Genderize[] genArray;
	
	@Test
	public void SingleNameTest(){
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to validate the response "+
				"of a simple request sent with one male name "+
				"\n ************************************************************** \n"
				);
		
		gen = API.getSingleRequest(API.appendName("Ivan"));
		
		//Validations
		Assert.assertTrue(gen.getName().equals("Ivan"));
		Assert.assertTrue(gen.getGender().equals("male"));
		Assert.assertTrue(! gen.getProbability().equals("0"));
		
		
	}
	
	@Test
	public void MultipleNamesTest(){
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to validate the response "+
				"of a complex request sent with multiple names of different genders "+
				"\n ************************************************************** \n"
				);
		
		genArray = API.getMultipleRequests(API.appendNames("Ivan", "Ramon","Maria"));
		
		//Validations
		Assert.assertTrue(genArray[0].getName().equals("Ivan"));
		Assert.assertTrue(genArray[0].getGender().equals("male"));
		Assert.assertTrue(! genArray[0].getProbability().equals("0"));
		
		Assert.assertTrue(genArray[1].getName().equals("Ramon"));
		Assert.assertTrue(genArray[1].getGender().equals("male"));
		Assert.assertTrue(! genArray[1].getProbability().equals("0"));
		
		Assert.assertTrue(genArray[2].getName().equals("Maria"));
		Assert.assertTrue(genArray[2].getGender().equals("female"));
		Assert.assertTrue(! genArray[2].getProbability().equals("0"));
	
	}
	
	@Test
	public void LocationTest(){	
		logger.info("\n  \n ************************************************************** \n"+
				"The purpose of this Test Case is to validate the response "+
				"of a request sent with one female name plus a localization param (country ID)"+
				"\n ************************************************************** \n"
				);
		gen = API.getSingleRequest(API.appendNameCountry("Kathy", "US"));
		
		//Validations
		Assert.assertTrue(gen.getName().equals("Kathy"));
		Assert.assertTrue(gen.getGender().equals("female"));
		Assert.assertTrue(! gen.getProbability().equals("0"));
		
	}
			
	@After
	public void disconnect(){
		API.disconnect();
	}

	

	@Rule 
	public TestRule classWatchman = new TestWatcher() {
		
	    @Override
	    protected void starting(Description desc) {
	    	logger = API.getLogger();
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
