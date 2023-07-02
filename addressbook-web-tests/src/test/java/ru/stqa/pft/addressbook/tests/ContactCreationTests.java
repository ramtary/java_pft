package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.nio.file.Path;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        Path avatar = Path.of("src/test/resources/avatar.jpg");
        ContactData contact = new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                .withNickname("ramtary").withPhoto(avatar.toAbsolutePath().toString()).withTitle("My contact")
                .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                .withByear("1990").withAday("1").withAmonth("January").withAyear("1990").withNewGroup("test1")
                .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}
