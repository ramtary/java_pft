package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File avatar = new File("src/test/resources/avatar.jpg");
        ContactData contact = new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                .withByear("1990").withAday("1").withAmonth("January").withAyear("1990").withNewGroup("test1")
                .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote");
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File avatar = new File("src/test/resources/avatar.jpg");
        ContactData contact = new ContactData().withFirstname("Alexey'").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                .withByear("1990").withAday("1").withAmonth("January").withAyear("1990").withNewGroup("test1")
                .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote");
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }

}
