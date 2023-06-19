package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.nio.file.Path;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationTest() {
        app.getNavigationHelper().goToToHomePage();
        app.getContactHelper().initContactModification();
        Path avatar = Path.of("src/test/resources/avatar_mod.jpg");
        app.getContactHelper().fillContactForm(new ContactData("Alexey", "Vladimirivich", "Krasnoschekov",
                "ramtary163", avatar.toAbsolutePath().toString(), "My contact", "PSB",
                "Nikolaiy Panova 51, 441", "+79376473503", "cloudmiin@gmail163.com",
                "cloudmiin1@gmail.com", "cloudmiin2@gmail.com", "+8462555555",
                "+8463232255", "+744477", "vk.com/test163", "1", "January",
                "1990", "1", "January", "1990", null,
                "Nikolaiy Panova 50, 442", "+793764733655", "testNote"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
