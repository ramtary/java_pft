package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            File avatar = new File("src/test/resources/avatar.jpg");
            app.contact().create(new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                    .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                    .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                    .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                    .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                    .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                    .withByear("1990").withAday("1").withAmonth("January").withAyear("1990").withGroup("test1")
                    .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote"));
        }
    }

    @Test
    public void contactModificationTest() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Alex").withMiddlename("Vlad")
                .withLastname("Kras").withNickname("ram").withTitle("My").withCompany("KPN").withAddress("Samara")
                .withMobilePhone("+8461").withEmail("1@1.com").withSecondEmail("2@2.com").withThirdEmail("3@3.com")
                .withHomePhone("+8462").withWorkPhone("+8463").withFax("+74").withHomepage("vk.com/test1")
                .withBmonth("January").withByear("1999").withAmonth("January").withAyear("1998").withSecondAddress("Moscow")
                .withSecondHomePhone("+123").withNotes("note2");
        app.contact().modify(contact);

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
        verifyContactListInUI();
    }
}
