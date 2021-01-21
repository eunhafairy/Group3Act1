package com.group3.group3act1;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.File;

public class Entry implements Parcelable{

    private Bitmap entryImage;
    private String entryName;
    private String entryRemark;



    private String birthdate;
    private String entryGender;
    private String entryAddress;
    private String entryContact;
    private String entryHobbies;
    private String otherInformation;

    public Entry(Bitmap entryImage, String entryName, String entryRemark, String birthdate, String entryGender, String entryAddress, String entryContact, String entryHobbies, String otherInformation) {
        this.setEntryImage(entryImage);
        this.setEntryName(entryName);
        this.setEntryRemark(entryRemark);
        this.setBirthdate(birthdate);
        this.setEntryGender(entryGender);
        this.setEntryAddress(entryAddress);
        this.setEntryContact(entryContact);
        this.setEntryHobbies(entryHobbies);
        this.setOtherInformation(otherInformation);
    }


    protected Entry(Parcel in) {
        entryName = in.readString();
        entryRemark = in.readString();
        entryGender = in.readString();
        entryAddress = in.readString();
        entryContact = in.readString();
        entryHobbies = in.readString();
        otherInformation = in.readString();
    }


    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Bitmap getEntryImage() {
        return entryImage;
    }

    public void setEntryImage(Bitmap entryImage) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(entryName);
        dest.writeString(entryRemark);
        dest.writeParcelable(entryImage, flags);
        dest.writeString(birthdate);
        dest.writeString(entryGender);
        dest.writeString(entryAddress);
        dest.writeString(entryContact);
        dest.writeString(entryHobbies);
        dest.writeString(otherInformation);
    }
}
