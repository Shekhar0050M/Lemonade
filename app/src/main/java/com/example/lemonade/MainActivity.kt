package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        var result by remember { mutableStateOf(1) }
        var decreaser by remember { mutableStateOf((2..4).random()) }
        val imageResource = when(result) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            4 -> R.drawable.lemon_restart
            else -> R.drawable.lemon_tree
        }
        val descriptionResource = when (result) {
            1 -> "Tap the lemon tree to select a lemon"
            2 -> "Keep tapping the lemon to squeeze it"
            3 -> "Tap the lemonade to drink it"
            4 -> "Tap the empty glass to start again"
            else -> "Tap the lemon tree to select a lemon"
        }
        when (result) {
            3 -> if (decreaser != 0) {
                result = result.plus(-1)
                decreaser = decreaser - 1
            }

            5 -> {
                result = 1
                decreaser = (2..4).random()
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { }) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = descriptionResource,
                        modifier = Modifier
                            .clickable { result = result.plus(1) }
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0xFF69CDD8)
                            )
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = descriptionResource,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}