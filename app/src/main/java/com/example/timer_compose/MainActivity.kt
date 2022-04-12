package com.example.timer_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

enum class Screen {
        Input,
        Countdown
    }

@Composable
 fun MyApp() {
    // The input of InputScreen is stored timeInSec and carried to CountdownScreen
    var timeInSec = 0
    Surface(color = MaterialTheme.colors.background) {
        //Use state to save and monitor changes to the current page
        var screen by remember { mutableStateOf(Screen.Input) }

        //Switch internal layouts that can be faded in and out;
        // internally switch different pages according to the screen.
        Crossfade(targetState = screen) {
            when (it) {
                Screen.Input -> InputScreen {
                    timeInSec = it
                    screen = Screen.Countdown
                }
                Screen.Countdown -> CountdownScreen(timeInSec) {
                    screen = Screen.Input
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimerComposeTheme {
        MyApp()
    }
}