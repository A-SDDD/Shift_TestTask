package com.danil.testtask.viewmodel


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.danil.testtask.AppContext
import com.danil.testtask.data.AppDatabase
import com.danil.testtask.data.FullUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val appDb: AppDatabase,
) : ViewModel(){
    val fullUsers = appDb.dao.getAllFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    val users = mutableStateOf(listOf<FullUserModel>())
    val valleyErrorCheck = mutableStateOf(false)
    val jsonErrorCheck = mutableStateOf(false)
    val selectedUser: MutableState<FullUserModel?> = mutableStateOf(null)


    fun insertUsers(
        item:MutableState<List<FullUserModel>>,
    ) = viewModelScope.launch {
            getUsers(item)
            appDb.dao.insertItem(item.value)
    }

    fun deleteUsers(list: List<FullUserModel>) = viewModelScope.launch{
        appDb.dao.delete(list)
    }

    private fun getUsers(
        list: MutableState<List<FullUserModel>>
    ){
        val url = "https://randomuser.me/api/?results=20"
        val queue = Volley.newRequestQueue(AppContext.appContext)
        val sRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                response ->
                list.value = parsingUsersInfo(response)
                Log.d("Response", response)
                Log.d("ShortUsers", list.value.toString())
                valleyErrorCheck.value = false
            },
            {
                valleyErrorCheck.value = true
                Log.d("Error", it.toString())
            }
        )
        queue.add(sRequest)
    }

    private fun parsingUsersInfo(response: String):List<FullUserModel>{
        if (response.isEmpty()) return listOf()
        else {
            val fullUsers = ArrayList<FullUserModel>()
            val mainObj = JSONObject(response)
            val results = mainObj.getJSONArray("results")
            try {
                for (i in 0 until results.length()) {
                    val result = results[i] as JSONObject
                    val gender = result.getString("gender")
                    val firstName = result.getJSONObject("name")
                        .getString("first")
                    val lastName = result.getJSONObject("name")
                        .getString("last")
                    val title = result.getJSONObject("name")
                        .getString("title")
                    val mediumPicture = result.getJSONObject("picture")
                        .getString("medium")
                    val bigPicture = result.getJSONObject("picture")
                        .getString("large")
                    val smallPicture = result.getJSONObject("picture")
                        .getString("thumbnail")
                    val country = result.getJSONObject("location")
                        .getString("country")
                    val state = result.getJSONObject("location")
                        .getString("state")
                    val city = result.getJSONObject("location")
                        .getString("city")
                    val streetName = result.getJSONObject("location")
                        .getJSONObject("street").getString("name")
                    val homeNumber = result.getJSONObject("location")
                        .getJSONObject("street").getString("number")
                    val postcode = result.getJSONObject("location")
                        .getString("postcode")
                    val latitude = result.getJSONObject("location")
                        .getJSONObject("coordinates")
                        .getString("latitude")
                    val longitude = result.getJSONObject("location")
                        .getJSONObject("coordinates")
                        .getString("longitude")
                    val timeZoneOffset = result.getJSONObject("location")
                        .getJSONObject("timezone")
                        .getString("offset")
                    val timeZoneDescription = result.getJSONObject("location")
                        .getJSONObject("timezone")
                        .getString("description")
                    val phoneNumber = result.getString("phone")
                    val email = result.getString("email")
                    val login = result.getJSONObject("login")
                    val uuid = login.getString("uuid")
                    val username = login.getString("username")
                    val password = login.getString("password")
                    val salt = login.getString("salt")
                    val md5 = login.getString("md5")
                    val sha1 = login.getString("sha1")
                    val sha256 = login.getString("sha256")
                    val birthday = result.getJSONObject("dob")
                        .getString("date")
                    val age = result.getJSONObject("dob")
                        .getString("age")
                    val registered = result.getJSONObject("registered")
                        .getString("date")
                    val regAge = result.getJSONObject("registered")
                        .getString("age")
                    val cell = result.getString("cell")
                    val idName = result.getJSONObject("id")
                        .getString("name")
                    val idValue = result.getJSONObject("id")
                        .getString("value")
                    val nat = result.getString("nat")
                    fullUsers.add(
                        FullUserModel(
                            gender = gender,
                            title = title,
                            firstName = firstName,
                            lastName = lastName,
                            streetName = streetName,
                            homeNumber = homeNumber,
                            city = city,
                            state = state,
                            country = country,
                            postcode = postcode,
                            latitude = latitude,
                            longitude = longitude,
                            timeZoneOffset = timeZoneOffset,
                            timeZoneDescription = timeZoneDescription,
                            email = email,
                            uuid = uuid,
                            username = username,
                            password = password,
                            salt = salt,
                            md5 = md5,
                            sha1 = sha1,
                            sha256 = sha256,
                            birthday = birthday,
                            age = age,
                            registered = registered,
                            regAge = regAge,
                            phoneNumber = phoneNumber,
                            cell = cell,
                            idName = idName,
                            idValue = idValue,
                            smallPicture = smallPicture,
                            mediumPicture = mediumPicture,
                            bigPicture = bigPicture,
                            nat = nat
                        )
                    )
                }
            } catch (e: JSONException){
                jsonErrorCheck.value = true
                return users.value
            }
            jsonErrorCheck.value = false
            return fullUsers
        }
    }
    fun callPhone(phoneNumber:String){
        val i = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:$phoneNumber")
        )

        try {
            AppContext.appContext.startActivity(i)
        } catch (s: SecurityException) {
            Toast.makeText(AppContext.appContext, "Что-то пошло не так", Toast.LENGTH_LONG)
                .show()
        }
    }
    fun callGmail(gmail: String){
        val i = Intent(
            Intent.ACTION_SENDTO,
            Uri.fromParts("mailto",gmail,null))
        try {
            AppContext.appContext.startActivity(i)
        } catch (s: SecurityException) {
            Toast.makeText(AppContext.appContext, "Что-то пошло не так", Toast.LENGTH_LONG)
                .show()
        }
    }
    fun callGmaps(latitude:String, longitude:String){
        val i = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:0,0?q=$latitude,$longitude")
        )
        try {
            AppContext.appContext.startActivity(i)
        } catch (s: SecurityException) {
            Toast.makeText(AppContext.appContext, "Что-то пошло не так", Toast.LENGTH_LONG)
                .show()
        }
    }
    fun callGmapsByAddress(address:String){
        val i = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:0,0?q=$address")
        )
        try {
            AppContext.appContext.startActivity(i)
        } catch (s: SecurityException) {
            Toast.makeText(AppContext.appContext, "Что-то пошло не так", Toast.LENGTH_LONG)
                .show()
        }
    }

}