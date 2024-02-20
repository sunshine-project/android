package com.sunshine.android.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunshine.android.R
import com.sunshine.android.domain.model.UserModel
import com.sunshine.android.ui.common.component.SunDialog
import com.sunshine.android.ui.feature.home.diary.DiaryDialog
import com.sunshine.android.ui.feature.home.quest.QuestDialog
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Red
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.theme.Yellow
import com.sunshine.android.util.TypewriterText


@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onQuestClick: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onNextTutorial = viewModel::nextTutorial,
        onQuestClick = onQuestClick,
        onLogout = onLogout,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNextTutorial: () -> Unit,
    onQuestClick: (Int) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier
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
            if (uiState.user != null) Profile(
                user = uiState.user,
            )
            Main(
                uiState = uiState,
                onQuestClick = onQuestClick,
                onLogout = onLogout,
                modifier = modifier.border(color = Red, width = 3.dp)
            )
        }
    }
}

@Composable
private fun Main(
    uiState: HomeUiState, onQuestClick: (Int) -> Unit, onLogout: () -> Unit, modifier: Modifier
) {

    var showQuestDialog by rememberSaveable { mutableStateOf(false) }
    var showDiaryDialog by rememberSaveable { mutableStateOf(false) }
    var showSwordDialog by rememberSaveable { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.img_battleground),
            contentDescription = "background",
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
                        .clickable {
                            showQuestDialog = true
                        }
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
                        .clickable {
                            showDiaryDialog = true
                        }
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
                        text = stringResource(R.string.home_until_kingdom_falls),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "D-${uiState.daysLeft}",
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
                        .padding(top = 40.dp, end = 150.dp)
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
                            .clickable { showQuestDialog = true }
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
                SunDialog(stringResource(R.string.home_cannot_pull_sword),
                    onDismiss = { showSwordDialog = false })
            }
            if (showQuestDialog) {
                QuestDialog(onDismiss = { showQuestDialog = false }, onQuestClick = {
                    showQuestDialog = false
                    onQuestClick(it)
                })
            }
            if (showDiaryDialog) {
                DiaryDialog(onDismiss = { showDiaryDialog = false }, onLogout = {
                    showDiaryDialog = false
                    onLogout()
                })
            }
        }
    }
}


@Composable
private fun Profile(user: UserModel, modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Yellow)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.img_character1),
                    contentDescription = "Profile",
                    modifier = modifier.size(width = 64.dp, height = 84.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Spacer(modifier = modifier.height(8.dp))
                Row {
                    Text(
                        text = user.name, style = Typography.bodyLarge.copy(
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        modifier = modifier
                            .background(color = Brown)
                            .padding(horizontal = 4.dp),
                        text = "Lv. ${user.level}",
                        style = Typography.bodyMedium.copy(
                            color = Color.White, fontSize = 14.sp
                        )
                    )
                }
            }
            Spacer(modifier = modifier.width(24.dp))
            Column(
                modifier = modifier
                    .background(color = LightBrown.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "STR",
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(28.dp)
                    )
                    LinearProgressIndicator(
                        progress = { user.str / 100f },
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        color = Red,
                    )
                    Text(
                        text = user.str.toString(),
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(24.dp),
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "SPI",
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(28.dp)
                    )
                    LinearProgressIndicator(
                        progress = { user.spi / 100f },
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        color = Color.Blue,
                    )
                    Text(
                        text = user.spi.toString(),
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(24.dp),
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "PEA",
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(28.dp)
                    )
                    LinearProgressIndicator(
                        progress = { user.pea / 100f },
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        color = Color.Green,
                    )
                    Text(
                        text = user.pea.toString(),
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(24.dp),
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "KNO",
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(28.dp)
                    )
                    LinearProgressIndicator(
                        progress = { user.kno / 100f },
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        color = Color.Yellow,
                    )
                    Text(
                        text = user.kno.toString(),
                        style = Typography.bodyMedium.copy(fontSize = 13.sp),
                        modifier = modifier.width(24.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
        LinearProgressIndicator(
            progress = { user.exp / user.expLeft.toFloat() },
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth(),
            color = Color.Cyan,
        )
    }
}