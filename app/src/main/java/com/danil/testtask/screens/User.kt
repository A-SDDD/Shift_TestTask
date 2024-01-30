package com.danil.testtask.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.danil.testtask.AppContext
import com.danil.testtask.viewmodel.UsersViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(
    onClickClose: () -> Unit,
    viewModel: UsersViewModel
){
    val scaffoldState = rememberScaffoldState()
    val selectedUser = remember{
        mutableStateOf(viewModel.selectedUser.value)
    }
    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = selectedUser.value?.firstName +
                                " " + selectedUser.value?.lastName,
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClickClose) {
                        Icon(
                            imageVector = Icons
                                .AutoMirrored
                                .Rounded
                                .ArrowBack,
                            contentDescription = "Close")
                    }
                }

            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(AppContext.appContext)
                    .data(selectedUser.value?.bigPicture)
                    .crossfade(true)
                    .build(),
                contentDescription = "user_photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, color = Color.Gray, CircleShape)
                    .size(120.dp, 120.dp)
            )
            Divider(
                color = Color.LightGray,
                thickness = 0.3.dp,
                modifier = Modifier.padding(top = 10.dp))

            Row(modifier = Modifier.fillMaxWidth()){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 15.dp, top = 15.dp)
                ){
                    Column {
                        Text(text ="Personal information", fontSize = 20.sp)
                        Text (text = "name: "+ selectedUser.value?.title +
                                selectedUser.value?.firstName +
                                selectedUser.value?.lastName,
                            fontSize = 18.sp
                        )
                        Text (text ="gender: "+  selectedUser.value?.gender,
                            fontSize = 18.sp)
                        Text (text ="age: " + selectedUser.value?.age+" y.o.",
                            fontSize = 18.sp)
                        Text(text ="date of birthday " +  selectedUser.value?.birthday,
                            fontSize = 18.sp)
                        Text (text ="nationality: " + selectedUser.value?.nat,
                            fontSize = 18.sp)

                        Text(text ="Contacts", fontSize = 20.sp)
                        Text (text = "tel.: "+ selectedUser.value?.phoneNumber,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                selectedUser.value?.phoneNumber?.let {
                                    phoneNumber ->
                                    viewModel.callPhone(
                                        phoneNumber
                                    )
                                }
                            }
                        )
                        Text (text ="cell.: "+  selectedUser.value?.cell,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                selectedUser.value?.cell?.let {
                                        phoneNumber ->
                                    viewModel.callPhone(
                                        phoneNumber
                                    )
                                }
                            })
                        Text (text ="email: " + selectedUser.value?.email,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                selectedUser.value?.email?.let {
                                    email ->
                                    viewModel.callGmail(
                                        email
                                    )
                                }
                            })
                        Text(text ="birth.: " +  selectedUser.value?.birthday,
                            fontSize = 18.sp)
                        Text (text ="nationality: " + selectedUser.value?.nat,
                            fontSize = 18.sp)

                        Text(text ="Location", fontSize = 20.sp)
                        Box(modifier = Modifier.clickable {
                                selectedUser.value?.country?.let { country ->
                                    selectedUser.value?.state?.let { state ->
                                        selectedUser.value?.city?.let { city ->
                                            selectedUser.value?.streetName?.let { street ->
                                                selectedUser.value?.homeNumber?.let { home ->
                                                    viewModel.callGmapsByAddress(
                                                        "$country," +
                                                                " $state," +
                                                                " $city," +
                                                                " $street, " +
                                                                home
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                        }){
                            Column {
                                Text (text = "country: "+ selectedUser.value?.country,
                                    fontSize = 18.sp)
                                Text (text ="state: "+  selectedUser.value?.state,
                                    fontSize = 18.sp)
                                Text (text ="city: " + selectedUser.value?.city,
                                    fontSize = 18.sp)
                                Text(text ="address: " +
                                        selectedUser.value?.streetName + ", "+
                                        selectedUser.value?.homeNumber,
                                    fontSize = 18.sp)
                            }

                        }

                        Text (text ="postcode: " + selectedUser.value?.postcode,
                            fontSize = 18.sp)
                        Text (text ="coordinate: " +
                                selectedUser.value?.latitude +"; "+
                            selectedUser.value?.longitude,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                selectedUser.value?.latitude?.let { latitude ->
                                    selectedUser.value?.longitude?.let { longitude ->
                                        viewModel.callGmaps(
                                            latitude, longitude
                                        )
                                    }
                                }
                            })
                        Text (text ="timezone: " +
                                selectedUser.value?.timeZoneDescription +"; "+
                                selectedUser.value?.timeZoneOffset,
                            fontSize = 18.sp)
                    }
                }
            }
        }
    }
}