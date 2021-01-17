package com.group3.group3act1;

public class Entry {

    private int entryImage;
    private String entryName;
    private String entryRemark;
    private String entryGender;
    private String entryAddress;
    private String entryContact;
    private String entryHobbies;
    private String otherInformation;

    public Entry(int entryImage, String entryName, String entryRemark, String entryGender, String entryAddress, String entryContact, String entryHobbies, String otherInformation) {
        this.setEntryImage(entryImage);
        this.setEntryName(entryName);
        this.setEntryRemark(entryRemark);
        this.setEntryGender(entryGender);
        this.setEntryAddress(entryAddress);
        this.setEntryContact(entryContact);
        this.setEntryHobbies(entryHobbies);
        this.setOtherInformation(otherInformation);
    }


    public int getEntryImage() {
        return entryImage;
    }

    public void setEntryImage(int entryImage) {
        this.entryImage = entryImage;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryRemark() {
        return entryRemark;
    }

    public void setEntryRemark(String entryRemark) {
        this.entryRemark = entryRemark;
    }

    public String getEntryGender() {
        return entryGender;
    }

    public void setEntryGender(String entryGender) {
        this.entryGender = entryGender;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public String getEntryContact() {
        return entryContact;
    }

    public void setEntryContact(String entryContact) {
        this.entryContact = entryContact;
    }

    public String getEntryHobbies() {
        return entryHobbies;
    }

    public void setEntryHobbies(String entryHobbies) {
        this.entryHobbies = entryHobbies;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }
}
