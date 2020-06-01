package me.i32xlevel.dzonetask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profession(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Worker(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String?,
    val avatarUrl: String?,
    val professions: List<Profession>
) : Parcelable