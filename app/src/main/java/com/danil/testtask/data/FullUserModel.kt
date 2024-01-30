package com.danil.testtask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_users")
data class FullUserModel (
    @PrimaryKey(autoGenerate = true) var id:Long? = null,
    val gender: String,
    val title:String,
    val firstName:String,
    val lastName:String,
    val streetName: String,
    val homeNumber: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
    val timeZoneOffset: String,
    val timeZoneDescription: String,
    val email: String,
    val uuid:String,
    val username:String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String,
    val birthday: String,
    val age: String,
    val registered: String,
    val regAge: String,
    val phoneNumber: String,
    val cell: String,
    val idName: String,
    val idValue: String,
    val smallPicture: String,
    val mediumPicture: String,
    val bigPicture: String,
    val nat: String
)