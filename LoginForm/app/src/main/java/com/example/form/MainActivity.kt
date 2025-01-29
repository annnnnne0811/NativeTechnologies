package com.example.form

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.* // For arranging components in a Column or Row
import androidx.compose.foundation.text.KeyboardOptions // To customize keyboard behavior
import androidx.compose.material.* // For Material Design components like Text, OutlinedTextField, and Button
import androidx.compose.runtime.Composable // For defining reusable UI components
import androidx.compose.runtime.remember // For state handling (not used in this example)
import androidx.compose.ui.Modifier // For layout customization (e.g., padding, width)
import androidx.compose.ui.text.input.KeyboardType // To set input types like email or password
import androidx.compose.ui.text.input.PasswordVisualTransformation // To hide password input
import androidx.compose.ui.unit.dp // For defining dimensions in Density-independent Pixels (dp)
import androidx.compose.ui.Alignment // For alignment in Column or Row
import androidx.compose.material.icons.Icons // For built-in icons
import androidx.compose.material.icons.filled.Email // Email icon
import androidx.compose.material.icons.filled.Lock // Lock icon
import androidx.compose.ui.tooling.preview.Preview // For previewing UI without running the app


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

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    LoginForm()
}