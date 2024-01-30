package com.danil.testtask.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.danil.testtask.viewmodel.UsersViewModel

@Composable
fun MainScreen (){
    val navController = rememberNavController()
    val viewModel: UsersViewModel = hiltViewModel()
    NavGraph(
        navController = navController,
        viewModel = viewModel,
    )

}