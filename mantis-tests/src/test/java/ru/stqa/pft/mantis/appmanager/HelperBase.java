package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!existingText.equals(text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void attach(By locator, File photo) {
        if (photo != null) {
            wd.findElement(locator).sendKeys(photo.getAbsolutePath());
        }
    }

    protected void selectByText(By locator, String text) {
        click(locator);
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

    protected void selectDefault(By locator) {
        click(locator);
        new Select(wd.findElement(locator)).selectByIndex(0);
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


}
