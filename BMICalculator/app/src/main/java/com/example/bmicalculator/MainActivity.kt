package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.Pink80
import kotlin.random.Random
import kotlin.text.*


class MainActivity  : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            BMICalculatorTheme {

                //A surface container using the "background" color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Bmi() //Will call the bmi composable function
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bmi(){

    //State variables store user input for height and weight
    var heightInput : String by remember { mutableStateOf("") }
    var weightInput : String by remember { mutableStateOf("") }



    //converting the input into a float value
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toFloatOrNull() ?: 0.0f


    //Calculates BMI
    val bmi = if (weight > 0 && height > 0) weight / ((height / 100) * (height / 100)) else 0.0



    Column {

        // title text
        Text(
            text = "BMI Calculator",
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)

        )//end for text



        //input for height
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',','.')},
            label = { Text("Height") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )//end



        //input for height
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',','.')},
            label = { Text("Weight") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )//end

        //box for the displaying BMI result
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .height(45.dp)
            .background(Pink80),
            contentAlignment = Alignment.Center
        ){
            //Result display
            Text(
                text = stringResource(R.string.result, String.format("%.2f", bmi).replace(',', '.'))
            )

        }





    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    BMICalculatorTheme {
        Bmi() //Preview function for the bmi composable
    }
}