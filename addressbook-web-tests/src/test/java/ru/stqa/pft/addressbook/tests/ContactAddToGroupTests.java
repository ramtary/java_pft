package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.io.File;
import java.util.stream.Collectors;

public class ContactAddToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        File avatar = new File("src/test/resources/avatar.jpg");
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                    .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                    .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                    .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                    .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                    .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                    .withByear("1990").withAday("1").withAmonth("January").withAyear("1990")
                    .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote"));

        }

        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        int contactId = before.iterator().next().getId();
        int groupId = groups.iterator().next().getId();

        app.goTo().homePage();
        app.contact().addToGroup(contactId, groupId);

        Contacts after = app.db().contacts();

        ContactData contactAddedToGroup = after.stream().filter(c -> c.getId() == contactId)
                .collect(Collectors.toSet()).iterator().next();

        Groups contactGroups = contactAddedToGroup.getGroups();

        Assert.assertTrue(contactGroups.stream()
                .map((g) -> new GroupData().withId(g.getId())).collect(Collectors.toSet())
                .contains(new GroupData().withId(groupId)));
    }
}
