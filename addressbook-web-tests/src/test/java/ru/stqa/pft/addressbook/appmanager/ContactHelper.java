package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

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
                selectByText(By.name("new_group"), contactData.getNewGroup());
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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> contactElements = wd.findElements(By.cssSelector("tr"));
        contactElements.remove(0);

        for (WebElement element : contactElements) {
            List<WebElement> contactDataElements = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = contactDataElements.get(1).getText();
            String firstname = contactDataElements.get(2).getText();

            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contactCache.add(contact);
        }

        return new Contacts(contactCache);
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        if (isAlertPresent()) {
            confirmContactDeletion();
        }
    }
}
