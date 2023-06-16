package ru.stqa.pft.addressbook.tests;

import java.nio.file.Path;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().addNewContact();
        Path avatar = Path.of("src/test/resources/avatar.jpg");
        app.getContactHelper().fillContactForm(new ContactData("Alexey", "Vladimirivich", "Krasnoschekov",
                "ramtary", avatar.toAbsolutePath().toString(), "My contact", "PSB",
                "Nikolaiy Panova 51, 441", "+79376473503", "cloudmiin@gmail.com",
                "cloudmiin1@gmail.com", "cloudmiin2@gmail.com", "+8462555555",
                "+8463232255", "+744477", "vk.com/test1", "1", "January",
                "1990", "1", "January", "1990", "test1",
                "Nikolaiy Panova 50, 442", "+793764733655", "testNote"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnHomePage();
    }

}
