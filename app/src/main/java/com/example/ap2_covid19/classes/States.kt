package com.example.ap2_covid19.classes

import android.os.Parcel
import android.os.Parcelable

class States(
    var name: String?,
    var cases: Int=0,
    var deaths: Int=0,
    var suspects: Int=0,
    var refuses: Int=0,
    var date: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(cases)
        parcel.writeInt(deaths)
        parcel.writeInt(suspects)
        parcel.writeInt(refuses)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<States> {
        override fun createFromParcel(parcel: Parcel): States {
            return States(parcel)
        }

        override fun newArray(size: Int): Array<States?> {
            return arrayOfNulls(size)
        }
    }
}