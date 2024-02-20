package com.sunshine.android.ui.feature.home.diary


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.sunshine.android.R
import com.sunshine.android.domain.model.JournalModel
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.util.TypewriterText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryDialog(onDismiss: () -> Unit, onLogout: () -> Unit) {
    val pages = listOf(
        "Album",
        "Journal",
        "Setting",
    )

    val quests = listOf(
        listOf(
            QuestModel(
                title = "5분 간 명상하기",
                description = "졸린 꾸벅이를 위해 " + "5분 동안 명상하고 마음을 진정시키세요.",
                exp = 10,
                statType = "STR",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
            QuestModel(
                title = "하늘 사진 찍기",
                description = "아름다운 하늘을 사진으로 남겨보세요.",
                exp = 10,
                statType = "SPI",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
            QuestModel(
                title = "먼 산 보기",
                description = "먼 곳을 바라보며 눈의 피로를 풀어주세요.",
                exp = 10,
                statType = "SPI",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
        ),
        listOf(
            QuestModel(
                title = "하늘 사진 찍기",
                description = "아름다운 하늘을 사진으로 남겨보세요.",
                exp = 10,
                statType = "SPI",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
        ),
        listOf(
            QuestModel(
                title = "먼 산 보기",
                description = "먼 곳을 바라보며 눈의 피로를 풀어주세요.",
                exp = 10,
                statType = "SPI",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
            QuestModel(
                title = "하늘 사진 찍기",
                description = "아름다운 하늘을 사진으로 남겨보세요.",
                exp = 10,
                statType = "SPI",
                statValue = 1,
                questType = QuestType.SHORT_ANSWER
            ),
        ),
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(modifier = Modifier.padding(vertical = 24.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.img_diary_bg),
                contentScale = ContentScale.FillBounds,
                contentDescription = "bg"
            )
            Column(horizontalAlignment = Alignment.End) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    indicator = {},
                    divider = {},
                    containerColor = Brown
                ) {
                    pages.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier
                                .background(color = if (selectedTabIndex == index) LightBrown else Brown)
                                .border(
                                    width = 2.dp, color = Brown
                                ),
                            selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                                coroutineScope.launch {
                                    pagerState.scrollToPage(selectedTabIndex)
                                }
                            },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            },
                            selectedContentColor = Brown,
                        )
                    }
                }
                HorizontalPager(
                    modifier = Modifier.weight(1f), state = pagerState, userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(
                                vertical = 16.dp, horizontal = 8.dp
                            ),
                            columns = GridCells.Fixed(2)
                        ) {
                            itemsIndexed(
                                listOf(
                                    "https://cdn1.epicgames.com/ue/product/Screenshot/Screenshot23-1920x1080-a2f8a3c1f88f3b5716bdd8c9a2ea0c28.jpg?resize=1&w=1920",
                                    "https://img.freepik.com/free-vector/pixel-art-mystical-background_52683-87349.jpg",
                                    "https://cdn1.epicgames.com/ue/product/Screenshot/Screenshot23-1920x1080-2d4c5b4a7155aca73a58b5f564caa11e.jpg?resize=1&w=1920",
                                )
                            ) { index, imageUrl ->
                                AlbumItem(imageUrl = imageUrl)
                            }
                        }

                        1 -> LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
                        ) {
                            itemsIndexed(
                                listOf(
                                    JournalModel(
                                        title = "Title1",
                                        answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                                    ), JournalModel(
                                        title = "Title2",
                                        answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                                    ), JournalModel(
                                        title = "Title3",
                                        answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                                    )
                                )
                            ) { index, journal ->
                                JournalItem(
                                    journal = journal, modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        2 -> Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedButton(
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                                onClick = {
                                    onLogout()
                                },
                                colors = ButtonColors(
                                    contentColor = Color.White,
                                    containerColor = Brown,
                                    disabledContainerColor = Brown,
                                    disabledContentColor = Color.White
                                ),
                                shape = RoundedCornerShape(0.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.diary_logout),
                                    style = Typography.bodyMedium.copy(
                                        fontSize = 16.sp, textAlign = TextAlign.Center
                                    )
                                )
                            }
                            ElevatedButton(
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                                onClick = {},
                                colors = ButtonColors(
                                    contentColor = Color.White,
                                    containerColor = Brown,
                                    disabledContainerColor = Brown,
                                    disabledContentColor = Color.White
                                ),
                                shape = RoundedCornerShape(0.dp),
                            ) {
                                Text(
                                    text = "Developer", style = Typography.bodyMedium.copy(
                                        fontSize = 16.sp, textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    }
                }
                ElevatedButton(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    onClick = {
                        onDismiss()
                    },
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Brown,
                        disabledContainerColor = Brown,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp),
                ) {
                    Text(
                        text = stringResource(R.string.common_close),
                        style = Typography.bodyMedium.copy(
                            fontSize = 16.sp, textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
}

@Composable
fun AlbumItem(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .background(color = Color.White)
            .height(164.dp),
        contentDescription = "album image",
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )
}

@Composable
fun JournalItem(journal: JournalModel, modifier: Modifier = Modifier) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = "book icon",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TypewriterText(text = journal.title, style = Typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(8.dp))
        TypewriterText(
            modifier = Modifier
                .background(color = Color.LightGray.copy(alpha = 0.5f))
                .padding(8.dp)
                .fillMaxWidth(), text = journal.answer, style = Typography.bodyMedium, minLines = 5
        )
    }
}