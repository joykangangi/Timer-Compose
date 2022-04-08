package com.example.timer_compose

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timer_compose.ui.theme.TimerComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.time.hours
import kotlin.time.times

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

    var disable by remember { mutableStateOf(false) }

    val startTimer =
        if (hourValue.isEmpty() || minValue.isEmpty() || secValue.isEmpty()) disable
        else {
            disable = !disable
            RunTimer(hourValue = hourValue, minValue = minValue, secValue = secValue)
        }
    Timer(
        hourValue = hourValue,
        onHourChanged = { hourValue = it },
        minValue = minValue,
        onMinChanged = { minValue = it },
        secValue = secValue,
        onSecChanged = { secValue = it },
        start = startTimer,

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
    start: Any
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
                    placeholder = { Text(text = "HH") },
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =KeyboardType.Number)
                )
                TextField(
                    value = minValue,
                    onValueChange =  onMinChanged,
                    placeholder = { Text(text = "MM")},
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =KeyboardType.Number)
                )
                TextField(
                    value = secValue,
                    onValueChange =  onSecChanged,
                    placeholder = { Text(text = "SS")},
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =KeyboardType.Number)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_play_circle),
            contentDescription = "Start Button",
            modifier = Modifier
                .clickable(
                    onClick = { start },
                    enabled = (hourValue.isEmpty() || minValue.isEmpty() || secValue.isEmpty())
                )
                .size(70.dp)
        )
    }
}

@Composable
fun RunTimer(hourValue: String, minValue: String, secValue: String) {
    val millis = (
            (hourValue.toInt()) * (3600000) +
                    (minValue.toInt()) * (60000) +
                    (secValue.toInt()) * (1000)
            )
   object : CountDownTimer(millis.toLong(), 1000) {
    override fun onTick(millisUntilFinished: Long) {

    }

    override fun onFinish() {

    }
}.start()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimerComposeTheme {
        MyApp()
    }
}