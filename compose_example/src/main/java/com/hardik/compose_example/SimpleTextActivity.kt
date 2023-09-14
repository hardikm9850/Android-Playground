package com.hardik.compose_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hardik.compose_example.ui.theme.PlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateLayout()
                }
            }
        }
    }
}

@Composable
fun CreateLayout(){
    Column {
        TextInCenter("Android", modifier = Modifier.padding(20.dp))
        TextWrapContent()
    }
}

@Composable
fun TextInCenter(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = Color.Blue,
        fontStyle = FontStyle.Normal,
        fontSize = 22.sp
    )
}

@Composable
fun TextWrapContent(){
    Text(text = "This is a wrap_content text", modifier = Modifier.wrapContentWidth())
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlaygroundTheme {
        CreateLayout()
    }
}