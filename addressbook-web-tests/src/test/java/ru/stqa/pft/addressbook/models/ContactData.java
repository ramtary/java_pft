package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Transient
    private String middlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Transient
    private String nickname;
    @Expose
    @Column(name = "photo")
    private String photo;
    @Expose
    @Transient
    private String title;
    @Expose
    @Transient
    private String company;
    @Expose
    @Transient
    private String address;
    @Expose
    @Column(name = "mobile")
    private String mobilePhone;
    @Expose
    @Transient
    private String email;
    @Expose
    @Transient
    private String secondEmail;
    @Expose
    @Transient
    private String thirdEmail;
    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Column(name = "home")
    private String homePhone;
    @Expose
    @Column(name = "work")
    private String workPhone;
    @Expose
    @Transient
    private String fax;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Transient
    private String homepage;
    @Expose
    @Transient
    private String bday;
    @Expose
    @Transient
    private String bmonth;
    @Expose
    @Transient
    private String byear;
    @Expose
    @Transient
    private String aday;
    @Expose
    @Transient
    private String amonth;
    @Expose
    @Transient
    private String ayear;
    @Expose
    @Transient
    private String group;
    @Expose
    @Transient
    private String secondAddress;
    @Expose
    @Transient
    private String secondHomePhone;
    @Expose
    @Transient
    private String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstname, that.firstname)) return false;
        return Objects.equals(lastname, that.lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getAday() {
        return aday;
    }

    public String getAmonth() {
        return amonth;
    }

    public String getAyear() {
        return ayear;
    }

    public String getGroup() {
        return group;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public String getSecondHomePhone() {
        return secondHomePhone;
    }

    public String getNotes() {
        return notes;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAday(String aday) {
        this.aday = aday;
        return this;
    }

    public ContactData withAmonth(String amonth) {
        this.amonth = amonth;
        return this;
    }

    public ContactData withAyear(String ayear) {
        this.ayear = ayear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
        return this;
    }

    public ContactData withSecondHomePhone(String secondHomePhone) {
        this.secondHomePhone = secondHomePhone;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
