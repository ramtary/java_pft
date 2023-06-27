package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.nio.file.Path;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToToHomePage();
        Path avatar = Path.of("src/test/resources/avatar_mod.jpg");
        if (app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Alexey", "Vladimirivich", "Krasnoschekov",
                    "ramtary", avatar.toAbsolutePath().toString(), "My contact", "PSB",
                    "Nikolaiy Panova 51, 441", "+79376473503", "cloudmiin@gmail.com",
                    "cloudmiin1@gmail.com", "cloudmiin2@gmail.com", "+8462555555",
                    "+8463232255", "+744477", "vk.com/test1", "1", "January",
                    "1990", "1", "January", "1990", "test1",
                    "Nikolaiy Panova 50, 442", "+793764733655", "testNote"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        if (app.getContactHelper().isAlertPresent()) {
            app.getContactHelper().confirmContactDeletion();
        }
        app.getNavigationHelper().goToToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
