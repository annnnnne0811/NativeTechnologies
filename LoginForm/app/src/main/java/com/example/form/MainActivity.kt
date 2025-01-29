package com.example.form

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.theme.BasicsCodelabTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent{
            LoginForm()
        }
    }
}

@Composable
fun LoginForm(){

    //Surface fills the screen with a background colour
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        //Column arranges components vertically
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, //centers the content vertically
            horizontalAlignment = Alignment.CenterHorizontally //centers the content horizontally
        ) {
            //Title Text
            Text(
                text = "Login", //Title
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp) // adds spacing below the title
            )

            //Username
            OutlinedTextField(
                value = "",              //current value of text field
                onValueChange = {},     //Handles changes to the text
                label = { Text(" Enter Username ")},    //a label inside the text field
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon")
                },
                modifier = Modifier.fillMaxSize(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)    //Display an email keyboard
            )

            Spacer(modifier = Modifier.height(16.dp))       //will add space between the fields

            //Password
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter Password ")},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                },
                visualTransformation = PasswordVisualTransformation(),  //Turns the input into dots
                modifier = Modifier.fillMaxSize(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))       //will add space between the fields

            //Submit Button
            Button(
                onClick = {},
                modifier = Modifier.fillMaxSize() //Makes the button fill the width of screen
            ) {

                // Text inside the button
                Text("Submit")
            }
        }
    }


}

@Composable
fun Surface(modifier: Modifier, color: Any, content: () -> Unit) {

}

@Composable
fun LoginScreen() {
    // UI elements like Text, Button, OutlinedTextField go here
    Text("Login")
}
