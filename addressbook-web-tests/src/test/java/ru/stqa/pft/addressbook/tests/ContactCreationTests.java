package ru.stqa.pft.addressbook.tests;

import com.google.gson.*;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase implements JsonDeserializer<File> {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(File.class, new ContactCreationTests())
                .create();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
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

    @Override
    public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new File(json.getAsString());
    }

}
