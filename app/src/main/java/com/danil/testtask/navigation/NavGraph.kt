package com.danil.testtask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danil.testtask.screens.User
import com.danil.testtask.screens.UserList
import com.danil.testtask.viewmodel.UsersViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: UsersViewModel,
){
    NavHost(
    navController = navController,
    startDestination = "user_list"
    ){
        composable("user_list"){
            UserList(
                onClickUser = {
                    navController.navigate("user_info")
                },
                viewModel
            )
        }
        composable("user_info"){
            User(
                onClickClose = {
                    navController.popBackStack()
                },
                viewModel
            )
        }
    }
}