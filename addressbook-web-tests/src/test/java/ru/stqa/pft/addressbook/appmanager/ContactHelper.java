package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactHelper extends HelperBase {
    public final Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);

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

        try {
            selectByText(By.name("bday"), contactData.getBday());
        } catch (Exception NoSuchElementException) {
            selectByIndex(By.name("bday"), 0);
        }

        try {
            selectByText(By.name("bmonth"), contactData.getBmonth());
        } catch (Exception NoSuchElementException) {
            selectByIndex(By.name("bmonth"), 0);
        }

        type(By.name("byear"), contactData.getByear());

        try {
            selectByText(By.name("aday"), contactData.getAday());
        } catch (Exception NoSuchElementException) {
            selectByIndex(By.name("aday"), 0);
        }

        try {
            selectByText(By.name("amonth"), contactData.getAmonth());
        } catch (Exception NoSuchElementException) {
            selectByIndex(By.name("amonth"), 0);
        }

        type(By.name("ayear"), contactData.getAyear());

        if (itsCreation) {
            try {
                selectByText(By.name("new_group"), contactData.getNew_group());
            } catch (Exception NoSuchElementException) {
                selectByIndex(By.name("new_group"), 0);
            }
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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return !isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> contactElements = wd.findElements(By.cssSelector("tr"));
        contactElements.remove(0);

        for (WebElement element : contactElements) {
            List<WebElement> contactDataElements = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = contactDataElements.get(1).getText();
            String firstname = contactDataElements.get(2).getText();

            ContactData contact = new ContactData(id, firstname, lastname);
            contacts.add(contact);
        }

        return contacts;
    }

    public void modifyContact(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }
}
