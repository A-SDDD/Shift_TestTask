package com.danil.testtask.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.danil.testtask.AppContext
import com.danil.testtask.data.FullUserModel
import com.danil.testtask.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShortUserCard(
    user: FullUserModel,
    onClickUser:()->Unit,
    viewModel: UsersViewModel
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp,
                end = 16.dp,
                top = 4.dp,
                bottom = 3.dp)
            .clip(AbsoluteCutCornerShape(bottomRight = 10.dp)),
        backgroundColor = Color.LightGray.copy(alpha = 0.7f),
        elevation = 0.dp,
        onClick = {
            onClickUser()
            viewModel.selectedUser.value = user
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(AppContext.appContext)
                            .data(user.mediumPicture)
                            .crossfade(true)
                            .build(),
                        contentDescription = "user_photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(Alignment.BottomStart)
                            .size(60.dp, 60.dp)
                    )
                }
                Column (
                    modifier = Modifier
                        .padding(start = 5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){

                        Box(
                            modifier= Modifier.fillMaxWidth()
                        ){
                            Row{
                                Text(
                                    text = user.title + ". "+
                                            user.firstName +
                                            " " + user.lastName,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Box(
                            modifier= Modifier.fillMaxWidth()
                        ){
                            Row {
                                Text(
                                    text = user.country +
                                            ", " + user.city,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row {
                                Text(
                                    text = "St. " + user.streetName +
                                            ", " + user.homeNumber,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                    Text(
                        text = "tel. " + user.phoneNumber,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
