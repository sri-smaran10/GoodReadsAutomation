package com.goodreads.testscript;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.goodreads.generic.BaseClass;
import com.goodreads.pom.RecentUpdatesPage;

public class AddBookAssignment extends BaseClass{
	@Test
	public void testAddAndRemoveBook() throws Throwable
	{
		RecentUpdatesPage r = new RecentUpdatesPage(driver);
		String book = fileUtils.readDataFromPropertyFile("book");
		r.getSearchBox().sendKeys(book);
		r.getBook().click();
		Actions action =new Actions(driver);
		WebElement wantToRead = r.getWantToReadBtn();
		action.doubleClick(wantToRead).perform();
		Thread.sleep(10000);
		WebElement closeBtn = r.getPopupCloseBtn();
		action.doubleClick(closeBtn).perform();
		r.getMyBookTab().click();
		r.getRemoveBookBtn().click();
		driver.switchTo().alert().accept();
	}
}
