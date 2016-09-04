package delta.main;

import org.openqa.selenium.By;

public class Locator {
	
	public static By getLocator(String input1){
		By b=null;
		String[] str = input1.split("_");
		String locator=str[0];
		String value=str[1];
		
		if(locator.equalsIgnoreCase("ID")){
			b=By.id(value);
		}
		else if(locator.equalsIgnoreCase("NAME")){
			b=By.name(value);
		}
		else if(locator.equalsIgnoreCase("XPATH")){
			b= By.xpath(value);
		}
		else if(locator.equalsIgnoreCase("className")){
			b=By.className(value);
			
			
		}
		else if(locator.equalsIgnoreCase("cssSelector"))
		{
			b=By.cssSelector(value);
			
		}
		else if(locator.equalsIgnoreCase("linkText"))
		{
			b=By.linkText(value);
				
		}
		else if(locator.equalsIgnoreCase("partialLinkText")){
			b=By.partialLinkText(value);
		}
		
		return b;
	}

}
