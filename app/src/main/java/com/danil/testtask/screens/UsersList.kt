package com.danil.testtask.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.danil.testtask.AppContext
import com.danil.testtask.viewmodel.UsersViewModel
import com.danil.testtask.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun UserList(
    onClickUser:()->Unit,
    viewModel: UsersViewModel
){
    val fullUsers = viewModel.fullUsers.collectAsState()


    if (fullUsers.value.isEmpty()) {
        viewModel.insertUsers(viewModel.users)
        if(viewModel.valleyErrorCheck.value){
            Toast.makeText(
                AppContext.appContext,
                "Ошибка соединения",
                Toast.LENGTH_LONG
            ).show()
        } else if (viewModel.jsonErrorCheck.value){
            Toast.makeText(
                AppContext.appContext,
                "Ошибка получения данных",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(
                        text = "TestTask",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteUsers(fullUsers.value)
                        if(viewModel.valleyErrorCheck.value){
                            Toast.makeText(
                                AppContext.appContext,
                                "Ошибка соединения",
                                Toast.LENGTH_LONG
                            ).show()
                            viewModel.valleyErrorCheck.value = false
                        }else if (viewModel.jsonErrorCheck.value){
                            Toast.makeText(
                                AppContext.appContext,
                                "Ошибка получения данных",
                                Toast.LENGTH_LONG
                            ).show()
                            viewModel.jsonErrorCheck.value = false
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_refresh_24),
                            contentDescription = "refresh" )
                    }
                }
            )
        }
    ){
        Image(painter = painterResource(id = R.drawable.background_shift),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .alpha(0.15f))
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1.0f)
        ) {
            itemsIndexed(fullUsers.value) {_, fullUserModel ->
                ShortUserCard(
                    fullUserModel,
                    onClickUser,
                    viewModel
                )
            }
        }
    }
}