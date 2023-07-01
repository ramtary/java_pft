package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;



public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("test2", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max(app.group().byId).get().getId());
        before.add(group);
        before.sort(app.group().byId);
        after.sort(app.group().byId);
        Assert.assertEquals(before, after);
    }

}
