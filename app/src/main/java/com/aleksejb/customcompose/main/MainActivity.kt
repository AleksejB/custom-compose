package com.aleksejb.customcompose.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aleksejb.customcompose.ui.theme.CustomComposeTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainContent() }


        val channel = Channel<Int> { Log.d("TAAAG", "the overflowed value is: $it") }

        GlobalScope.launch {
            for (i in 1..10) channel.send(i)
        }

        GlobalScope.launch {
            for (i in 11..20) channel.send(i)
        }

        GlobalScope.launch {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                val int = channel.receive()
                list.add(int)
            }
            Log.d("TAAAG", "the recieved int is: $list")
        }

    }
}