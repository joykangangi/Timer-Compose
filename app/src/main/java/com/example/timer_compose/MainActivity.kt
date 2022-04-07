package com.example.timer_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timer_compose.ui.theme.TimerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable //managing states
fun MyApp() {
    var hourValue by remember { mutableStateOf("") }
    var minValue by remember { mutableStateOf("") }
    var secValue by remember { mutableStateOf("") }
    
    Timer(
        hourValue = hourValue ,
        onHourChanged = { hourValue = it },
        minValue = minValue,
        onMinChanged = { minValue = it },
        secValue = secValue,
        onSecChanged = {secValue = it}
    )
}
@Composable
fun Timer(
    hourValue: String,
    onHourChanged: (String) -> Unit,
    minValue: String,
    onMinChanged: (String) -> Unit,
    secValue: String,
    onSecChanged: (String) -> Unit,
) {
    Column(Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Timer duration?", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.medium
            ) {
                TextField(
                    value = hourValue,
                    onValueChange =  onHourChanged,
                    placeholder = { Text(text = "HH")},
                    modifier = Modifier.size(60.dp)
                )
                TextField(
                    value = minValue,
                    onValueChange =  onMinChanged,
                    placeholder = { Text(text = "MM")},
                    modifier = Modifier.size(60.dp)
                )
                TextField(
                    value = secValue,
                    onValueChange =  onSecChanged,
                    placeholder = { Text(text = "SS")},
                    modifier = Modifier.size(60.dp)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_play_circle),
            contentDescription = "Start Button",
            modifier = Modifier.clickable(onClick = { })
                .size(70.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimerComposeTheme {
        MyApp()
    }
}