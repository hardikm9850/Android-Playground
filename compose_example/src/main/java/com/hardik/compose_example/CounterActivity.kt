package com.hardik.compose_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hardik.compose_example.ui.theme.PlaygroundTheme

class CounterActivity : ComponentActivity() {
    private val TAG = CounterActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterExample()
                }
            }
        }
    }

    @Composable
    fun CounterExample() {
        val count = remember { mutableStateOf(0) }
        Column {
            Spacer(modifier = Modifier.size(14.dp, 14.dp))
            Button(modifier = Modifier.padding(14.dp), onClick = {
                count.value++
            }) {
                SimpleText("Click me", Modifier.padding(10.dp))
            }
            SimpleText(
                "You have clicked the button: " + count.value.toString(),
                Modifier.padding(24.dp)
            )
        }
    }

    @Composable
    fun SimpleText(message: String, modifier: Modifier) {
        Text(text = message, modifier = modifier)
    }

    @Preview
    @Composable
    fun Preview() {
        CounterExample()
    }
}