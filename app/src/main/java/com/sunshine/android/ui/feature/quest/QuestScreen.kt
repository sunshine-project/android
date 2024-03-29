package com.sunshine.android.ui.feature.quest

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sunshine.android.R
import com.sunshine.android.domain.model.QuestType
import com.sunshine.android.ui.common.component.SunDialog
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.DarkBrown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Orange
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.theme.Yellow

@Composable
internal fun QuestRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    QuestScreen(
        onFinish = onFinish,
        onComplete = { viewModel.completeQuest(context) },
        onTextChange = viewModel::updateInputText,
        onPhotoChange = viewModel::updatePhoto,
        uiState = uiState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun QuestScreen(
    onFinish: () -> Unit,
    onComplete: () -> Unit,
    onTextChange: (String) -> Unit,
    onPhotoChange: (Uri) -> Unit,
    uiState: QuestUiState,
    modifier: Modifier = Modifier
) {
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) onPhotoChange(uri)
    }

    val canShowFinishDialog = rememberSaveable { mutableStateOf(true) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = LightBrown)
    ) {
        if (uiState is QuestUiState.Success && uiState.isFinish && canShowFinishDialog.value) {
            SunDialog(stringResource(R.string.quest_congrats), onDismiss = {
                canShowFinishDialog.value = false
                onFinish()
            })
        }
        Image(
            painter = painterResource(id = R.drawable.img_scroll_bg),
            contentDescription = "bg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onFinish()
                        })
            }
            Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Column(
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                        .height(IntrinsicSize.Max),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    when (uiState) {
                        is QuestUiState.Loading -> {
                            Text(
                                text = "Loading...", color = DarkBrown
                            )
                        }

                        is QuestUiState.Success -> {
                            val quest = uiState.quest
                            Image(
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(96.dp)
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
                            Spacer(modifier = Modifier.height(24.dp))
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                FlowRow(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = quest.title, style = Typography.bodyLarge.copy(
                                            fontSize = 20.sp,
                                            color = Color.Black,
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Image(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(
                                            id = when (quest.questType) {
                                                QuestType.SHORT_ANSWER -> R.drawable.ic_short_answer
                                                QuestType.PHOTO -> R.drawable.ic_image
                                                QuestType.ROUTINE -> R.drawable.ic_redo
                                                QuestType.TIMER -> R.drawable.ic_clock
                                                else -> R.drawable.ic_short_answer
                                            }
                                        ),
                                        contentDescription = "quest type",
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = quest.description,
                                    style = Typography.bodyMedium.copy(
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                        lineHeight = 24.sp,
                                        textAlign = TextAlign.Center,
                                    ),
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "${quest.statType}+${quest.statValue} EXP+${quest.exp}",
                                    style = Typography.bodyMedium.copy(
                                        fontSize = 16.sp, color = Orange
                                    ),
                                )
                                Spacer(modifier = Modifier.height(48.dp))
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    when (quest.questType) {
                                        QuestType.SHORT_ANSWER -> {
                                            Text(
                                                text = stringResource(R.string.quest_short_answer_mission),
                                                style = Typography.bodyMedium.copy(
                                                    fontSize = 14.sp,
                                                    color = Color.Gray,
                                                    lineHeight = 20.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            BasicTextField(
                                                modifier = modifier
                                                    .fillMaxWidth()
                                                    .background(color = Yellow)
                                                    .padding(16.dp),
                                                minLines = 5,
                                                value = uiState.inputText,
                                                onValueChange = onTextChange,
                                                textStyle = Typography.bodyMedium.copy(
                                                    color = Color.Black,
                                                    fontSize = 18.sp,
                                                    textAlign = TextAlign.Center,
                                                ),
                                                decorationBox = { innerTextField ->
                                                    Column(
                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                    ) {
                                                        innerTextField()
                                                        Spacer(modifier = modifier.height(8.dp))
                                                    }
                                                },
                                            )
                                        }

                                        QuestType.PHOTO -> {
                                            Text(
                                                text = stringResource(R.string.quest_photo_mission),
                                                style = Typography.bodyMedium.copy(
                                                    fontSize = 14.sp,
                                                    color = Color.Gray,
                                                    lineHeight = 20.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            AsyncImage(
                                                model = uiState.photo,
                                                modifier = Modifier
                                                    .background(
                                                        color = Color.LightGray.copy(
                                                            alpha = 0.5f
                                                        )
                                                    )
                                                    .size(96.dp)
                                                    .clickable {
                                                        imagePicker.launch("image/*")
                                                    }
                                                    .padding(if (uiState.photo == null) 8.dp else 0.dp),
                                                fallback = painterResource(
                                                    id = R.drawable.ic_image,
                                                ),
                                                colorFilter = if (uiState.photo == null) ColorFilter.tint(
                                                    Color.Gray
                                                ) else null,
                                                contentDescription = "quest type",
                                                contentScale = if (uiState.photo == null) ContentScale.Fit else ContentScale.Crop,
                                            )
                                        }

                                        QuestType.DAILY, QuestType.ROUTINE -> {
                                            Text(
                                                text = stringResource(R.string.quest_routine_mission),
                                                style = Typography.bodyMedium.copy(
                                                    fontSize = 14.sp,
                                                    color = Color.Gray,
                                                    lineHeight = 20.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                        }

                                        QuestType.TIMER -> {
                                            Text(
                                                text = stringResource(R.string.quest_timer_mission),
                                                style = Typography.bodyMedium.copy(
                                                    fontSize = 14.sp,
                                                    color = Color.Gray,
                                                    lineHeight = 20.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                        }
                                    }
                                }
                                ElevatedButton(
                                    enabled = when (uiState.quest.questType) {
                                        QuestType.SHORT_ANSWER -> uiState.inputText.isNotBlank()
                                        QuestType.PHOTO -> uiState.photo != null
                                        else -> true
                                    },
                                    modifier = Modifier.padding(
                                        horizontal = 24.dp, vertical = 24.dp
                                    ),
                                    onClick = {
                                        onComplete()
                                    },
                                    colors = ButtonColors(
                                        contentColor = Color.White,
                                        containerColor = Brown,
                                        disabledContainerColor = Color.Gray,
                                        disabledContentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(0.dp),
                                ) {
                                    Text(
                                        text = stringResource(R.string.queset_complete_quest),
                                        style = Typography.bodyMedium.copy(
                                            fontSize = 16.sp, textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }
                        }

                        is QuestUiState.Error -> {
                            Text(
                                text = "Could not load the quest.", color = DarkBrown
                            )
                        }
                    }
                }
            }
        }
    }
}