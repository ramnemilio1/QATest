package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassionTeaPO {
	private WebDriver driver;
	
	public PassionTeaPO(WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getTab(String tab){
		String xpath=".//a[contains(text(), '"+tab+"')]";
		return driver.findElement(By.xpath(xpath));
	}

	public WebElement getHeaderLabel(String tab){
		String xpath=".//h1[contains(text(), '"+tab+"')]";
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getCollectionHerbalTea(){
		String xpath=".//span[contains(text(), 'See Collection')]";
		return driver.findElements(By.xpath(xpath)).get(0);
	}
	
	public WebElement getCollectionLooseTea(){
		String xpath=".//span[contains(text(), 'See Collection')]";
		List<WebElement> list =driver.findElements(By.xpath(xpath));
		return driver.findElements(By.xpath(xpath)).get(1);
	}
	
	public WebElement getCollectionFlavoredTea(){
		String xpath=".//span[contains(text(), 'See Collection')]";
		return driver.findElements(By.xpath(xpath)).get(2);
	}
	
	
	public WebElement getNameField(){
		String xpath=".//div/label[contains(text(), 'Name')]/../input";
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getEmailField(){
		String xpath=".//div/label[contains(text(), 'Email')]/../input";
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getSubjectField(){
		String xpath=".//div/label[contains(text(), 'Subject')]/../input";
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getMessageField(){
		String xpath=".//textarea";
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement getSubmitButton(){
		String xpath=".//input[contains(@value, 'Submit')]";
		return driver.findElement(By.xpath(xpath));
	}
	
	
	
	
	
	
	

}
