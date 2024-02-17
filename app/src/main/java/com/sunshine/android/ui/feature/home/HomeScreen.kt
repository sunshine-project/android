package com.sunshine.android.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunshine.android.R
import com.sunshine.android.data.model.UserModel
import com.sunshine.android.ui.common.component.SunDialog
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.Red
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.theme.Yellow
import com.sunshine.android.util.TypewriterText


@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState, onNextTutorial = viewModel::nextTutorial, modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState, onNextTutorial: () -> Unit, modifier: Modifier
) {
    Box {
        if (uiState.showTutorial) Box(modifier = modifier
            .fillMaxSize()
            .zIndex(1f)
            .clickable {
                onNextTutorial()
            }) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.BottomCenter)
                    .padding(24.dp),
                horizontalAlignment = Alignment.End,
            ) {
                TypewriterText(
                    modifier = modifier.fillMaxWidth(),
                    text = uiState.currentTutorial.content,
                    style = Typography.titleSmall.copy(
                        color = Color.White, lineHeight = 30.sp, fontSize = 18.sp
                    ),
                )
                Image(
                    modifier = modifier.size(144.dp),
                    painter = painterResource(id = R.drawable.img_host),
                    contentDescription = "host",
                )
            }
        }
        Column(
            modifier = modifier.border(color = Brown, width = 3.dp, shape = RectangleShape)
        ) {
            Profile(
                user = uiState.user,
            )
            Main(day = 70, uiState = uiState, modifier = modifier.border(color = Red, width = 3.dp))
        }
    }
}

@Composable
private fun Main(day: Int, uiState: HomeUiState, modifier: Modifier) {

    var showSwordDialog by remember { mutableStateOf(false) }
    var showQuestionMartDialog by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.img_battleground),
            contentDescription = "배경",
            modifier = Modifier
                .fillMaxHeight()
                .size(1600.dp),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), color = Color.Transparent
        ) {
            Column(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.btn_quest),
                    contentDescription = "Quest",
                    modifier = Modifier
                        .padding(5.dp)
                        .width(60.dp)
                        .fillMaxWidth()
                        .clickable { }
                        .border(
                            color = if (uiState.currentTutorial.event == TutorialEvent.SHOW_QUEST) Red else Color.Transparent,
                            width = 2.dp,
                            shape = RoundedCornerShape(8.dp)
                        ))
                Image(painter = painterResource(id = R.drawable.btn_diary),
                    contentDescription = "Diary",
                    modifier = Modifier
                        .padding(5.dp)
                        .width(60.dp)
                        .fillMaxWidth()
                        .clickable { }
                        .border(
                            color = if (uiState.currentTutorial.event == TutorialEvent.SHOW_DIARY) Red else Color.Transparent,
                            width = 2.dp,
                            shape = RoundedCornerShape(8.dp)
                        ))
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp)
                        .border(
                            color = if (uiState.currentTutorial.event == TutorialEvent.SHOW_DAYS) Red else Color.Transparent,
                            width = 2.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(4.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "왕국 멸망까지",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "D-${day}",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Red,
                        )
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 100.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.btn_sword),
                    contentDescription = "sword",
                    modifier = Modifier
                        .padding(top = 40.dp, end = 120.dp)
                        .width(100.dp)
                        .fillMaxWidth()
                        .clickable { showSwordDialog = true })
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 50.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.quest),
                        contentDescription = "quest",
                        modifier = Modifier
                            .padding(bottom = 200.dp)
                            .size(50.dp)
                            .clickable { showQuestionMartDialog = true }
                            .border(
                                color = if (uiState.currentTutorial.event == TutorialEvent.SHOW_NOTI && uiState.showTutorial) Red else Color.Transparent,
                                width = 2.dp,
                                shape = RoundedCornerShape(8.dp)
                            ))
                    Image(
                        painter = painterResource(id = R.drawable.img_character1),
                        contentDescription = "player",
                        modifier = Modifier.size(128.dp)
                    )
                }

            }
            if (showSwordDialog) {
                SunDialog("아직 검을 뽑을 수 없어요!", onDismiss = { showSwordDialog = false })
            }
            if (showQuestionMartDialog) {
                SunDialog("완료하지 않은 퀘스트가 있어요!", onDismiss = { showQuestionMartDialog = false })
            }
        }
    }
}


@Composable
private fun Profile(user: UserModel?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .border(color = Brown, width = 2.dp, shape = RectangleShape)
            .fillMaxWidth()
            .background(color = Yellow),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (user == null) {
            Text(
                text = "유저를 불러오는 중입니다...", modifier = Modifier.padding(20.dp)
            )
            return
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile",
            modifier = Modifier
                .padding(40.dp)
                .border(color = Brown, width = 3.dp, shape = RectangleShape)
        )
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Name: ${user.name}",
            )
            Text(
                text = "Leve: ${user.level}",
            )
            Text(
                text = "Stats: ",
            )
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Text(
                    text = "STR: ${user.str}",
                )
                Text(
                    text = "SPI: ${user.spi}",
                )
                Text(
                    text = "PEA: ${user.pea}",
                )
                Text(
                    text = "KNO: ${user.kno}",
                )
            }
        }
    }
}