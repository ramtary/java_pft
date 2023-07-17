package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.*;
import ru.stqa.pft.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator implements JsonSerializer<File> {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException parameterException) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(File.class, new ContactDataGenerator())
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File avatar = new File("src/test/resources/avatar.jpg");
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int day = random.nextInt(28) + 1;
            int year = random.nextInt(2023) + 1000;
            contacts.add(new ContactData().withFirstname(String.format("firstname %s", i))
                    .withMiddlename(String.format("middlename %s", i)).withLastname(String.format("lastname %s", i))
                    .withNickname(String.format("nickname %s", i)).withPhoto(avatar).withTitle(String.format("title %s", i))
                    .withCompany(String.format("company %s", i)).withAddress(String.format("address %s", i))
                    .withMobilePhone(String.format("+%s%s", i, i)).withEmail(String.format("email%s@gmail.com", i))
                    .withSecondEmail(String.format("email%s@yandex.com", i)).withThirdEmail(String.format("email%s@psbank.com", i))
                    .withHomePhone(String.format("+%s%s%s", i, i, i)).withWorkPhone(String.format("+%s%s%s%s", i, i, i, i))
                    .withFax(String.format("+%s", i)).withHomepage(String.format("vk.com/%s", i)).withBday(Integer.toString(day))
                    .withBmonth("January").withByear(Integer.toString(year)).withAday(Integer.toString(day)).withAmonth("January")
                    .withAyear(Integer.toString(year))
                    .withSecondAddress(String.format("secondAddress %s", i)).withSecondHomePhone(String.format("+%s%s", i, i))
                    .withNotes(String.format("note %s", i)));
        }
        return contacts;
    }

    @Override
    public JsonElement serialize(File file, Type typeOfFile, JsonSerializationContext context) {
        return context.serialize(file.getPath());
    }
}
