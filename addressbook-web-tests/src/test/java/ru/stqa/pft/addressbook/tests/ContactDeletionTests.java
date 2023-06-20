package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.nio.file.Path;

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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmContactDeletion();
    }
}
