package com.sunshine.android.presention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunshine.android.R
import com.sunshine.android.data.UserInfo
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.SunshineTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SunshineTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(

    ) {
        Profile(UserInfo("david", 1, 10, 5, 3, 7))
        Main()
    }


}

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
    ) {
        // 이미지 A 표시
        Image(
            painter = painterResource(id = R.drawable.btn_diary),
            contentDescription = "Quest",
            modifier = Modifier
                .padding(16.dp)
                .width(50.dp)
                .fillMaxWidth()
        )

        // 이미지 B 표시
        Image(
            painter = painterResource(id = R.drawable.btn_diary),
            contentDescription = "Diary",
            modifier = Modifier
                .padding(16.dp)
                .width(50.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Profile(info: UserInfo, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.border(color = Brown, width = 2.dp, shape = RectangleShape),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile",
            modifier = Modifier
                .padding(40.dp)
                .border(color = Brown, width = 3.dp, shape = RectangleShape)
        )
        Column(
            modifier = Modifier.padding(30.dp)
        )
        {
            Text(
                text = "Name: ${info.name}",
            )
            Text(
                text = "Leve: ${info.level}",
            )
            Text(
                text = "Stats: ",
            )
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Text(
                    text = "STR: ${info.str}",
                )
                Text(
                    text = "SPI: ${info.spi}",
                )
                Text(
                    text = "PEA: ${info.pea}",
                    style = typography.bodyLarge
                )
                Text(
                    text = "KNO: ${info.kno}",
                    style = typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    Profile(UserInfo("david", 1, 10, 5, 3, 7), Modifier.fillMaxSize())
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(Modifier.fillMaxSize())
}
