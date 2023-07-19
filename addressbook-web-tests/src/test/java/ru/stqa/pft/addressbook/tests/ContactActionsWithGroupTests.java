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

public class ContactActionsWithGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        File avatar = new File("src/test/resources/avatar.jpg");
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Alexey").withMiddlename("Vladimirivich").withLastname("Krasnoschekov")
                    .withNickname("ramtary").withPhoto(avatar).withTitle("My contact")
                    .withCompany("PSB").withAddress("Nikolaiy Panova 51, 441").withMobilePhone("+79376473503")
                    .withEmail("cloudmiin@gmail.com").withSecondEmail("cloudmiin1@gmail.com")
                    .withThirdEmail("cloudmiin2@gmail.com").withHomePhone("+8462555555").withWorkPhone("+8463232255")
                    .withFax("+744477").withHomepage("vk.com/test1").withBday("1").withBmonth("January")
                    .withByear("1990").withAday("1").withAmonth("January").withAyear("1990")
                    .withSecondAddress("Nikolaiy Panova 50, 442").withSecondHomePhone("+793764733655").withNotes("testNote"));

        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData contactForAdd = before.iterator().next();
        int contactId = contactForAdd.getId();
        int groupId = groups.iterator().next().getId();

        // если контакт в группе, удалим его
        if (isGroupContainsContact(groupId, contactForAdd)) {
            app.goTo().homePage();
            app.contact().removeFromGroup(contactId, groupId);
        }

        app.goTo().homePage();
        app.contact().addToGroup(contactId, groupId);

        Contacts after = app.db().contacts();

        Assert.assertTrue(isGroupContainsContact(groupId, getMovedContact(contactId, after)));
    }

    @Test
    public void testContactRemoveFromGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData contactForRemove = before.iterator().next();
        int contactId = contactForRemove.getId();
        int groupId = groups.iterator().next().getId();

        // если контакта нет в группе, добавим его
        if (!isGroupContainsContact(groupId, contactForRemove)) {
            app.goTo().homePage();
            app.contact().addToGroup(contactId, groupId);
        }

        app.goTo().homePage();
        app.contact().removeFromGroup(contactId, groupId);

        Contacts after = app.db().contacts();

        Assert.assertFalse(isGroupContainsContact(groupId, getMovedContact(contactId, after)));
    }

    private static boolean isGroupContainsContact(int groupId, ContactData contact) {
        return contact.getGroups().stream()
                .map((g) -> new GroupData().withId(g.getId())).collect(Collectors.toSet())
                .contains(new GroupData().withId(groupId));
    }

    private static ContactData getMovedContact(int contactId, Contacts contacts) {
        return contacts.stream().filter(c -> c.getId() == contactId)
                .collect(Collectors.toSet()).iterator().next();
    }
}
