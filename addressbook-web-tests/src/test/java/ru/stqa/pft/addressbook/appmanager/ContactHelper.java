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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
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

        attach(By.name("photo"), contactData.getPhoto());

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
            selectDefault(By.name("bday"));
        }

        try {
            selectByText(By.name("bmonth"), contactData.getBmonth());
        } catch (Exception NoSuchElementException) {
            selectDefault(By.name("bmonth"));
        }

        type(By.name("byear"), contactData.getByear());

        try {
            selectByText(By.name("aday"), contactData.getAday());
        } catch (Exception NoSuchElementException) {
            selectDefault(By.name("aday"));
        }

        try {
            selectByText(By.name("amonth"), contactData.getAmonth());
        } catch (Exception NoSuchElementException) {
            selectDefault(By.name("amonth"));
        }

        type(By.name("ayear"), contactData.getAyear());

        if (itsCreation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertEquals(contactData.getGroups().size(), 1);
                selectByText(By.name("new_group"), contactData.getGroups().iterator().next().getName());
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
        click(By.cssSelector(String.format("input[name='selected[]'][value='%s']", id)));
    }

    private void selectGroupForAddById(int groupId) {
        click(By.cssSelector(String.format("select[name='to_group'] > option[value='%s']", groupId)));
    }

    private void selectGroupPageById(int groupId) {
        click(By.cssSelector(String.format("select[name='group'] > option[value='%s']", groupId)));
    }

    private void addSelectedContactToGroup() {
        click(By.xpath("//input[@value='Add to']"));
    }

    private void removeSelectedContactFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
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
        List<WebElement> contactRows = wd.findElements(By.cssSelector("tr"));
        contactRows.remove(0);

        for (WebElement element : contactRows) {
            List<WebElement> contactDataCells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = contactDataCells.get(1).getText();
            String firstname = contactDataCells.get(2).getText();
            String address = contactDataCells.get(3).getText();
            String allEmails = contactDataCells.get(4).getText();
            String allPhones = contactDataCells.get(5).getText();

            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
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

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String secondHomePhone = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withEmail(email).withSecondEmail(secondEmail).withThirdEmail(thirdEmail)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withSecondHomePhone(secondHomePhone);
    }

    public void addToGroup(int contactId, int groupId) {
        selectContactById(contactId);
        selectGroupForAddById(groupId);
        addSelectedContactToGroup();
    }

    public void removeFromGroup(int contactId, int groupId) {
        selectGroupPageById(groupId);
        selectContactById(contactId);
        removeSelectedContactFromGroup();
    }
}
