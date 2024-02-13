package com.sunshine.android.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunshine.android.R
import com.sunshine.android.data.UserInfo
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.Red
import com.sunshine.android.ui.theme.Yellow


@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(modifier = modifier)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.border(color = Brown, width = 3.dp, shape = RectangleShape)
    ) {
        Profile(UserInfo("david", 1, 10, 5, 3, 7))
        Main(70)
    }
}

@Composable
fun Main(day: Int) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(color = Color.Magenta),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_quest),
                contentDescription = "Quest",
                modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.btn_diary),
                contentDescription = "Diary",
                modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
                    .fillMaxWidth()
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(10.dp),
        ) {
            Text(
                text = "왕국 멸망까지",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "D-${day}",
                textAlign = TextAlign.End,
                color = Red,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun Profile(info: UserInfo, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .border(color = Brown, width = 2.dp, shape = RectangleShape)
            .fillMaxWidth()
            .background(color = Yellow),
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
                )
                Text(
                    text = "KNO: ${info.kno}",
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