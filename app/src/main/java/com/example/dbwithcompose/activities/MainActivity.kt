package com.example.dbwithcompose.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dbwithcompose.Utils.Routes
import com.example.dbwithcompose.db.MainDataBase
import com.example.dbwithcompose.ui.AddList.AddListScreen
import com.example.dbwithcompose.ui.list.ListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           val navController = rememberNavController( )
            NavHost(navController = navController, startDestination = Routes.ITEM_LIST){
composable(Routes.ITEM_LIST){
    ListScreen(
        onNavigate = {
            navController.navigate(it.route)
        }
    )
}
                composable (
                    route = Routes.ADD_ITEM_LIST + "?itemId= {itemId}",
                    arguments= listOf(
                        navArgument(name="itemId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                        ){
                    AddListScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }
            }

    }
}

