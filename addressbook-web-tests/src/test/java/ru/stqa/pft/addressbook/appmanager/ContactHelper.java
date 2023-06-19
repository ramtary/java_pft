package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean itsCreation) {
        type(By.name("firstname"), contactData.getFirstname());

        type(By.name("middlename"), contactData.getMiddlename());

        type(By.name("lastname"), contactData.getLastname());

        type(By.name("nickname"), contactData.getNickname());

        addPhoto(By.name("photo"), contactData.getPhoto());

        type(By.name("title"), contactData.getTitle());

        type(By.name("company"), contactData.getCompany());

        type(By.name("address"), contactData.getAddress());

        type(By.name("mobile"), contactData.getMobilePhone());

        type(By.name("email"), contactData.getEmail());

        type(By.name("email2"), contactData.getSecondEmail());

        type(By.name("email3"), contactData.getThirdEmail());

        type(By.name("home"), contactData.getHomePhone());

        type(By.name("work"), contactData.getWorkPhone());

        type(By.name("fax"), contactData.getFax());

        type(By.name("homepage"), contactData.getHomepage());

        select(By.name("bday"), contactData.getBday());

        select(By.name("bmonth"), contactData.getBmonth());

        type(By.name("byear"), contactData.getByear());

        select(By.name("aday"), contactData.getAday());

        select(By.name("amonth"), contactData.getAmonth());

        type(By.name("ayear"), contactData.getAyear());

        if (itsCreation) {
            select(By.name("new_group"), contactData.getNew_group());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address2"), contactData.getSecondAddress());

        type(By.name("phone2"), contactData.getSecondHomePhone());

        type(By.name("notes"), contactData.getNotes());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void createContact(ContactData contactData, boolean itsCreation) {
        initContactCreation();
        fillContactForm(contactData, itsCreation);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return !isElementPresent(By.name("selected[]"));
    }
}
