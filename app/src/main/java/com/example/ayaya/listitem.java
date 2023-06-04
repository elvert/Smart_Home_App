package com.example.ayaya;

import android.app.LauncherActivity;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class listitem implements Parcelable {

    private String Card_ID, Status, Tanggal;
    public listitem(String Card_ID, String Status, String Tanggal)
    {
        this.Card_ID = Card_ID;
        this.Status = Status;
        this.Tanggal = Tanggal;
    }

    public String getCard_id() {
        return Card_ID;
    }

    public String getStatus() {
        return Status;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setCard_id(String Card_ID) {
        this.Card_ID = Card_ID;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(this.Card_ID);
        parcel.writeString(this.Status);
        parcel.writeString(this.Tanggal);
    }

    protected listitem(Parcel in) {
        this.Card_ID = in.readString();
        this.Status = in.readString();
        this.Tanggal = in.readString();
    }

    public static final Parcelable.Creator<listitem> CREATOR = new Parcelable.Creator<listitem>(){
        @Override
        public listitem createFromParcel(Parcel source) {
            return new listitem(source);
        }

        @Override
        public listitem[] newArray(int size) {
            return new listitem[size];
        }
    };
}
