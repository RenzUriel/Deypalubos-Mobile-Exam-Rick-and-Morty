package com.mobileexam.deypalubos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mobileexam.deypalubos.ui.theme.RickAndMorty2Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobileexam.deypalubos.screens.CharacterDetailScreen
import com.mobileexam.deypalubos.screens.CharacterListScreen
import com.mobileexam.deypalubos.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMorty2Theme {
                val navController = rememberNavController()
                val characterViewModel: CharacterViewModel = viewModel()

                NavHost(navController = navController, startDestination = "character_list") {
                    composable("character_list") {
                        CharacterListScreen(navController, characterViewModel)
                    }
                    composable("character_detail/{characterId}") { backStackEntry ->
                        val characterId = backStackEntry.arguments?.getString("characterId")?.toInt()
                        characterId?.let {
                            CharacterDetailScreen(navController, characterViewModel, it)
                        }
                    }
                }
            }
        }
    }
}
