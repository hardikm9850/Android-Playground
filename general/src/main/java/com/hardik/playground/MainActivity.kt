package com.hardik.playground

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.hardik.playground.ui.theme.PlaygroundTheme
import com.hardik.playground.work_manager.UploadWorker

class MainActivity : ComponentActivity() {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContent {
            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun scheduleOneTimeWork() {
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .build()
        WorkManager.getInstance(context).enqueue(uploadWorkRequest)

    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)) {
            Button(onClick = {

            }) {
                Text("OneTime work request")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { }, modifier = Modifier.height(40.dp)) {
                Text("Submit Request")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.height(40.dp),
                onClick = {
                    scheduleOneTimeWork()
                }) {
                Text(text = "Click to begin")
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PlaygroundTheme {
            Greeting("Android")
        }
    }
}


