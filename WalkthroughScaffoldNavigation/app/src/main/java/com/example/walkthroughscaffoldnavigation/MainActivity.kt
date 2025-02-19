package com.example.walkthroughscaffoldnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walkthroughscaffoldnavigation.ui.theme.Pink80
import com.example.walkthroughscaffoldnavigation.ui.theme.Purple80
import com.example.walkthroughscaffoldnavigation.ui.theme.WalkthroughScaffoldNavigationTheme
import kotlin.math.round

//Composable function for the top bar used for info and settings screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()})  {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
    )
}

//Composable function for the main top bar used in the main home screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, navController:NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {/*do something */}) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {expanded =! expanded}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Open submenu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {navController.navigate("info")}
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {navController.navigate("settings")}
                )
            }
        },
    )
}


//Home Screen composable function
@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = { MainTopAppBar("My App", navController) },
        ){ innerPadding ->
        Text(text = "This is the Home Screen",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding))
    }
}

//Info screen when user press the info on the navigation bar
@Composable
fun InfoScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("Info", navController) },
    ){ innerPadding ->
        Text(text = "This is the Info Screen",
            modifier = Modifier
                .background(Pink80) //Changes background color
                .fillMaxSize()
                .padding(innerPadding))
    }
}

//Settings screen user press the settings from navigation bar
@Composable
fun SettingScreen (navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("Settings", navController) },
    ) { innerPadding ->
        Text(text = "This is the Settings Screen", modifier = Modifier
            .background(Purple80) // Changes background Color
            .fillMaxSize()
            .padding(innerPadding))
    }
}

@Composable
fun ScaffoldApp(){
    val navController = rememberNavController() //Handles the screen navigation
    NavHost(
        navController = navController,
        startDestination = "home" //Default screen
    ){
        composable (route = "home"){ MainScreen(navController) }
        composable( route = "info"){ InfoScreen(navController) }
        composable(route = "settings") { SettingScreen(navController) }

    }
}



//Main activity composable
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            WalkthroughScaffoldNavigationTheme{
                ScaffoldApp() //launches the main composable function
            }
        }
    }

}
