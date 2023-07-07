package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        File avatar = new File("src/test/resources/avatar_mod.jpg");
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                    .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                    .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                    .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                    .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                    .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                    .withByear("1990").withAday("1").withAmonth("January").withAyear("1990").withNewGroup("test1")
                    .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote"));

        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondHomePhone())
                .filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
