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
import com.danil.testtask.ui.theme.LightBlue
import com.danil.testtask.viewmodel.UsersViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(
    onClickClose: () -> Unit,
    viewModel: UsersViewModel
){
    val scaffoldState = rememberScaffoldState()
    val selectedUser = viewModel.selectedUser


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
                            contentDescription = "Close"
                        )
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
                thickness = 1.dp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Box(modifier = Modifier.padding(end = 15.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 15.dp, top = 15.dp)
                ) {
                    Text(
                        text = "Personal information",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )


                    Row {
                        Text(
                            text = "title: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = selectedUser.value?.title + ".",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "firstname: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.firstName}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "lastname: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.lastName}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "gender: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.gender}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "age: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.age}",
                            fontSize = 18.sp
                        )
                    }

                    Row {
                        Text(
                            text = "birth.: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.birthday}",
                            fontSize = 18.sp
                        )
                    }

                    Row {
                        Text(
                            text = "nationality: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.nat}",
                            fontSize = 18.sp
                        )
                    }

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        text = "Contacts",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)
                    )
                    Row {
                        Text(
                            text = "tel.: ",
                            fontSize = 18.sp
                        )
                        Text(text = "${selectedUser.value?.phoneNumber}",
                            fontSize = 18.sp,
                            color = LightBlue,
                            modifier = Modifier.clickable {
                                selectedUser.value?.phoneNumber?.let { phoneNumber ->
                                    viewModel.callPhone(
                                        phoneNumber
                                    )
                                }
                            }
                        )
                    }

                    Row {
                        Text(
                            text = "cell.: ",
                            fontSize = 18.sp
                        )
                        Text(text = "${selectedUser.value?.cell}",
                            fontSize = 18.sp,
                            color = LightBlue,
                            modifier = Modifier.clickable {
                                selectedUser.value?.cell?.let { phoneNumber ->
                                    viewModel.callPhone(
                                        phoneNumber
                                    )
                                }
                            }
                        )
                    }

                    Row {
                        Text(
                            text = "email: ",
                            fontSize = 18.sp
                        )
                        Text(text = "${selectedUser.value?.email}",
                            fontSize = 18.sp,
                            color = LightBlue,
                            modifier = Modifier.clickable {
                                selectedUser.value?.email?.let { email ->
                                    viewModel.callGmail(
                                        email
                                    )
                                }
                            }
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        text = "Location",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)
                    )
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
                    }) {
                        Column {
                            Row {
                                Text(
                                    text = "country: ",
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "${selectedUser.value?.country}",
                                    fontSize = 18.sp,
                                    color = LightBlue
                                )
                            }
                            Row {
                                Text(
                                    text = "state: ",
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "${selectedUser.value?.state}",
                                    fontSize = 18.sp,
                                    color = LightBlue
                                )
                            }
                            Row {
                                Text(
                                    text = "city: ",
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "${selectedUser.value?.city}",
                                    fontSize = 18.sp,
                                    color = LightBlue
                                )

                            }
                            Row {
                                Text(
                                    text = "address: ",
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = selectedUser.value?.streetName + ", " +
                                            selectedUser.value?.homeNumber,
                                    fontSize = 18.sp,
                                    color = LightBlue
                                )
                            }
                        }
                    }


                    Row {
                        Text(
                            text = "postcode: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.postcode}",
                            fontSize = 18.sp
                        )
                    }

                    Row {
                        Text(
                            text = "coordinate: ",
                            fontSize = 18.sp
                        )
                        Text(text = selectedUser.value?.latitude + "; " +
                                selectedUser.value?.longitude,
                            fontSize = 18.sp,
                            color = LightBlue,
                            modifier = Modifier.clickable {
                                selectedUser.value?.latitude?.let { latitude ->
                                    selectedUser.value?.longitude?.let { longitude ->
                                        viewModel.callGmaps(
                                            latitude, longitude
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Row {
                        Text(
                            text = "timezone: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = selectedUser.value?.timeZoneDescription + "; " +
                                    selectedUser.value?.timeZoneOffset,
                            fontSize = 18.sp
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        text = "Registration",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)
                    )
                    Row {
                        Text(
                            text = "date: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.registered}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "age: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.regAge}",
                            fontSize = 18.sp
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        text = "Private information",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 6.dp)
                    )

                    Text(
                        text = "uuid: ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${selectedUser.value?.uuid}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )

                    Row {
                        Text(
                            text = "username: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.username}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "password: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.password}",
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        Text(
                            text = "salt: ",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${selectedUser.value?.salt}",
                            fontSize = 18.sp
                        )
                    }

                    Text(
                        text = "md5: ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${selectedUser.value?.md5}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )


                    Text(
                        text = "sha1: ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${selectedUser.value?.sha1}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )


                    Text(
                        text = "sha256: ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${selectedUser.value?.sha256}",
                        fontSize = 18.sp
                    )

                }
            }
        }
    }
}