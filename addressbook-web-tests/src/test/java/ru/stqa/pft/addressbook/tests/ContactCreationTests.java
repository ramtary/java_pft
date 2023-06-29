package ru.stqa.pft.addressbook.tests;

import java.nio.file.Path;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        Path avatar = Path.of("src/test/resources/avatar.jpg");
        ContactData contact = new ContactData("Alexey", "Vladimirivich", "Krasnoschekov",
                "ramtary", avatar.toAbsolutePath().toString(), "My contact", "PSB",
                "Nikolaiy Panova 51, 441", "+79376473503", "cloudmiin@gmail.com",
                "cloudmiin1@gmail.com", "cloudmiin2@gmail.com", "+8462555555",
                "+8463232255", "+744477", "vk.com/test1", "1", "January",
                "1990", "1", "January", "1990", "test1",
                "Nikolaiy Panova 50, 442", "+793764733655", "testNote");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max(app.getContactHelper().byId).get().getId());
        before.add(contact);
        before.sort(app.getContactHelper().byId);
        after.sort(app.getContactHelper().byId);
        Assert.assertEquals(before, after);

    }

}
