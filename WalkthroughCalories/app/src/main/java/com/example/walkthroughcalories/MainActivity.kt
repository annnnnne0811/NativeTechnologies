package com.example.walkthroughcalories

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.walkthroughcalories.ui.theme.WalkthroughCaloriesTheme
import kotlin.math.exp
import kotlin.math.sin

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //lets ui extend to the edge of the screen
        setContent{
            WalkthroughCaloriesTheme { // applies the theme
                Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalorieApp(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}




//Composable function for displaying a heading
@Composable
fun Heading (title: String){
    Text(
        text = title,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 16.dp
            )
    )
}



@Composable
fun CalorieApp (modifier: Modifier = Modifier){
    var weightInput by remember { mutableStateOf("") }
    var weight = weightInput.toIntOrNull() ?:0
    var male by remember { mutableStateOf(true)}
    var intensity by remember { mutableStateOf(1.3f) }
    var result by remember { mutableStateOf(0) }

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        //Displaying heading
        Heading("Calories")
        //weight input field
        WeightField(weightInput = weightInput , onValueChange = {weightInput = it})
        //Gender Selection
        GenderChoices(male, setGenderMale ={male = it} )
        //Intensity Selection
        IntensityList(onClick = {intensity = it})
        //Display results
        Text(
            text = result.toString(),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )

        //Calculation Button
        Calculation(
            male = male,
            weight = weight,
            intensity = intensity,
            setResult = {result = it}
        )
    }
}





//Composable function for weight input field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightField(weightInput: String, onValueChange: (String)-> Unit) {
    OutlinedTextField(
        value = weightInput,
        onValueChange = onValueChange,
        label = {Text(text="Enter Weight")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun GenderChoices (male : Boolean, setGenderMale:(Boolean)->Unit){
    Column {

        //Row for Male Selection
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = male,
                onClick = {setGenderMale(true)}
            )
            Text(text = "Male")
        }

        //Row for the female selection
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = !male,   //if male is NOT selected, the radio button is clicked
                onClick = {setGenderMale(false)}    //updates the state to female (false)
            )
            Text(text = "Female")   //label for the female radio button
        }
    }
}



@Composable
fun IntensityList(onClick: (Float) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Light") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var items = listOf("Light", "Usual", "Moderate", "Hard", "Very Hard")


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            readOnly = true,
            value = selectedText,
            onValueChange = {selectedText = it},
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text("Select intensity") },
            trailingIcon = {
                Icon(icon,"Select intensity",
                    Modifier.clickable { expanded =! expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})

        ) //End of Dropdown Menu
        {
            items.forEach{ item ->
                val intensity: Float = when (item){
                    "Light" -> 1.3f
                    "Usual" -> 1.5f
                    "Moderate" -> 1.7f
                    "Hard" -> 2f
                    "Very hard" -> 2.2f
                    else -> 0.0f

                }//End items

                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onClick(intensity)
                        selectedText = item
                        expanded = false
                    }

                )//End Drop down menu item

            }
        }
    }
}

// Composable function for displaying the result and calculation button
@Composable
fun Calculation(male:Boolean, weight: Int, intensity: Float, setResult:(Int) -> Unit){
    Button(
        onClick = {
            if (male){
                setResult(((879 + 10.2 * weight)* intensity).toInt())
            }else{
                setResult(((795 + 7.18 * weight)* intensity).toInt())
            }

        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Calculate")

    }
}


@Preview(showBackground = true)
@Composable
fun CalorieAppPreview(){
    WalkthroughCaloriesTheme{
        CalorieApp()
    }
}