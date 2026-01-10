package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme

import android.content.res.Configuration

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)
@Composable
fun MessageCard(msg: Message){
    // Padding around the message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            // Here we choose the image and edit its properties
            painter = painterResource(R.drawable.minecraft_2024_cover_art),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        // Adds horizontal space between image and columns
        Spacer(modifier = Modifier.width(8.dp))
        // Tracks whether message is expanded or not
        var isExpanded by remember { mutableStateOf(false) }

        // Toggles the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable {isExpanded = !isExpanded}) {
        Text(text = msg.author,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge)

        // Vertical space between author and message
        Spacer(modifier = Modifier.width(4.dp))

        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
            Text(text = msg.body,
                modifier = Modifier.padding(all = 4.dp),
                // If the message is expanded, we show it's contents
                // else we only show the first line
                maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages) { message -> MessageCard(message)
        }
    }
}
