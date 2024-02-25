package com.sunshine.android.ui.feature.home.quest


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sunshine.android.R
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Orange
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.common.component.TypewriterText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestDialog(
    quests: List<List<QuestModel>>, onDismiss: () -> Unit, onQuestClick: (Int) -> Unit
) {
    val pages = listOf(
        "today",
        "routine",
        "completed",
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
                painter = painterResource(id = R.drawable.img_scroll_bg),
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
                    if (quests[page].isEmpty()) TypewriterText(
                        text = stringResource(R.string.quest_no_quest),
                        style = Typography.bodyMedium.copy(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    else LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        itemsIndexed(quests[page]) { _, quest ->
                            QuestItem(quest = quest, modifier = if (page < 2) Modifier.clickable {
                                onQuestClick(quest.id)
                            } else Modifier)
                        }
                    }
                }
                ElevatedButton(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
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
fun QuestItem(quest: QuestModel, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .width(84.dp)
                .height(64.dp)
                .background(color = Color.White)
                .border(width = 3.dp, color = Color.Black),
            painter = painterResource(
                id = when (quest.statType) {
                    "STR" -> R.drawable.img_god_str
                    "SPI" -> R.drawable.img_god_spi
                    "PEA" -> R.drawable.img_god_pea
                    else -> R.drawable.img_god_kno
                }
            ),
            contentDescription = "quest",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Spacer(modifier = modifier.width(8.dp))
        Column(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
        ) {
            TypewriterText(
                text = quest.title, style = Typography.bodyLarge.copy(
                    fontSize = 14.sp, color = Color.Black
                )
            )
            Spacer(modifier = modifier.height(8.dp))
            TypewriterText(
                text = quest.description,
                style = Typography.bodyMedium.copy(
                    fontSize = 12.sp, color = Color.Black, lineHeight = 16.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = modifier.height(8.dp))
            TypewriterText(
                text = (if (quest.statValue > 0) "${quest.statType}+${quest.statValue} " else "") + "EXP+${quest.exp}",
                style = Typography.bodyMedium.copy(
                    fontSize = 12.sp, color = Orange
                ),
            )
        }
    }
}

