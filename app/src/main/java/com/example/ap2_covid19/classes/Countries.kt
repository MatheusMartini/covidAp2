package com.example.ap2_covid19.classes

import android.os.Parcel
import android.os.Parcelable

class Countries(
    var name: String?,
    var cases: Int=0,
    var confirmed: Int=0,
    var deaths: Int=0,
    var recovered: Int=0,
    var date: String?
) :Parcelable {
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
        parcel.writeInt(confirmed)
        parcel.writeInt(deaths)
        parcel.writeInt(recovered)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Countries> {
        override fun createFromParcel(parcel: Parcel): Countries {
            return Countries(parcel)
        }

        override fun newArray(size: Int): Array<Countries?> {
            return arrayOfNulls(size)
        }
    }
}