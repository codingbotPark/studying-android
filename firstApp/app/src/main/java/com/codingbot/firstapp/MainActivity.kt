package com.codingbot.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingbot.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Grettings(listOf("Android","Bruno","Park"))
                }
            }
        }
    }
}

@Composable
fun Grettings(names:List<String>){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(4.dp)){
        for (name in names){
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var state = remember{ mutableStateOf(true)}
    Column {
        Text(text = if(state.value) "Hello!"+name else "Bye"+name )
        ElevatedButton(onClick = { state.value = !state.value }) {
            Text(
                text = if(state.value) "Bye!" else "Hello"
            )
        }
    }  

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAppTheme {
        Surface (modifier = Modifier.fillMaxSize(),color = MaterialTheme.colorScheme.primary){
            Grettings(listOf("Android","Bruno","Park"))
        }

    }
}