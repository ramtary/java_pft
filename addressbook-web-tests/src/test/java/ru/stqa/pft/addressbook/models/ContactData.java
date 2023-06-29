package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String photo;
    private final String title;
    private final String company;
    private final String address;
    private final String mobilePhone;
    private final String email;
    private final String secondEmail;
    private final String thirdEmail;
    private final String homePhone;
    private final String workPhone;
    private final String fax;
    private final String homepage;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String aday;
    private final String amonth;
    private final String ayear;
    private final String new_group;
    private final String secondAddress;
    private final String secondHomePhone;

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

    private final String notes;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String photo,
                       String title, String company, String address, String mobilePhone, String email, String secondEmail,
                       String thirdEmail, String homePhone, String workPhone, String fax, String homepage, String bday, String bmonth,
                       String byear, String aday, String amonth, String ayear, String new_group, String secondAddress,
                       String secondHomePhone, String notes) {
        this.id = 0;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.photo = photo;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.secondEmail = secondEmail;
        this.thirdEmail = thirdEmail;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.fax = fax;
        this.homepage = homepage;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.aday = aday;
        this.amonth = amonth;
        this.ayear = ayear;
        this.new_group = new_group;
        this.secondAddress = secondAddress;
        this.secondHomePhone = secondHomePhone;
        this.notes = notes;
    }

    public ContactData(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = null;
        this.nickname = null;
        this.photo = null;
        this.title = null;
        this.company = null;
        this.address = null;
        this.mobilePhone = null;
        this.email = null;
        this.secondEmail = null;
        this.thirdEmail = null;
        this.homePhone = null;
        this.workPhone = null;
        this.fax = null;
        this.homepage = null;
        this.bday = null;
        this.bmonth = null;
        this.byear = null;
        this.aday = null;
        this.amonth = null;
        this.ayear = null;
        this.new_group = null;
        this.secondAddress = null;
        this.secondHomePhone = null;
        this.notes = null;
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

    public String getPhoto() {
        return photo;
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

    public String getNew_group() {
        return new_group;
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

    public void setId(int id) {
        this.id = id;
    }
}
