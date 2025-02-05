package com.example.theming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theming.ui.theme.ThemingTheme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ThemingTheme {

                //Surface using the background colour  from the theme
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MyApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){

    //Modifier for applying the composable
    val appModifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)

    //Column composable to arrange vertically
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        //Text composable for displaying the title
        Text(
            text = "My title",
            style = MaterialTheme.typography.titleLarge,
            modifier = appModifier
        )



        //OutlinedTextField composable for input
        OutlinedTextField(
            value = "",
            onValueChange = {/*TODO*/},
            modifier = appModifier
        )



        //Button composable for user interaction
        Button(
            onClick = {/*TODO*/},
            modifier = appModifier

        ) {
            //text composable inside the button
            Text(text = "Submit")

        }

    }//End vertical Arrangement
}//End Myapp



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemingTheme {


        //Calls the main Composable function for the preview
        MyApp()
    }
}