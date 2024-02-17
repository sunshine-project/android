package com.sunshine.android.ui.feature.onboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunshine.android.R
import com.sunshine.android.ui.theme.Red
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.util.TypewriterText

@Composable
internal fun OnboardRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardViewModel = hiltViewModel(),
) {
    val uiState by viewModel.onboardUiState.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    OnboardScreen(
        uiState = uiState,
        error = error,
        onShowAnswer = viewModel::showAnswer,
        onNextClick = viewModel::nextDialogOrEvent,
        onFinish = onFinish,
        onRegisterName = viewModel::registerName,
        onRegisterGender = viewModel::registerGender,
        onRegisterWarning = viewModel::registerWarning,
        onRegisterStat = viewModel::registerStat,
        modifier = modifier,
    )
}

@Composable
internal fun OnboardScreen(
    uiState: OnboardUiState,
    error: String?,
    onShowAnswer: () -> Unit,
    onNextClick: () -> Unit,
    onFinish: () -> Unit,
    onRegisterName: (String) -> Unit,
    onRegisterGender: (Int) -> Unit,
    onRegisterWarning: (Boolean) -> Unit,
    onRegisterStat: (Int) -> Unit,
    modifier: Modifier,
) {
    when (uiState.currentEvent) {
        OnboardEvent.ONBOARD_NAME -> OnboardNameDialog(onConfirm = onRegisterName, error = error)
        OnboardEvent.ONBOARD_GENDER -> OnboardGenderDialog(onConfirm = onRegisterGender)
        OnboardEvent.ONBOARD_WARNING -> OnboardWarningDialog(
            currentPage = uiState.currentWarningPage, onConfirm = onRegisterWarning
        )

        OnboardEvent.ONBOARD_STAT -> OnboardStatDialog(
            currentPage = uiState.currentStatPage, onConfirm = onRegisterStat
        )

        OnboardEvent.ONBOARD_FINISH -> onFinish()

        else -> {}
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = modifier.size(200.dp),
                contentDescription = "onboard image",
                painter = painterResource(
                    id = if (uiState.currentDialog.isHostWithLantern) R.drawable.img_host_with_lamp
                    else R.drawable.img_host_with_shadow
                ),
            )
            Spacer(modifier = modifier.height(24.dp))
            TypewriterText(
                modifier = modifier.fillMaxWidth(),
                text = uiState.currentDialog.content.replace(
                    "&name", uiState.name ?: ""
                ).replace("&stat1", uiState.stat[0].toString())
                    .replace("&stat2", uiState.stat[1].toString())
                    .replace("&stat3", uiState.stat[2].toString())
                    .replace("&stat4", uiState.stat[3].toString()),
                style = Typography.bodyMedium.copy(
                    color = colorResource(id = R.color.white),
                    lineHeight = 32.sp,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                ),
                onFinish = onShowAnswer
            )
        }
        Spacer(modifier = modifier.height(24.dp))
        ElevatedButton(
            modifier = modifier
                .fillMaxWidth()
                .alpha(if (uiState.isShowAnswer) 1f else 0f),
            onClick = onNextClick,
            colors = ButtonColors(
                contentColor = Color.Black,
                containerColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            ),
            shape = RoundedCornerShape(0.dp),
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = uiState.currentDialog.answer,
                style = Typography.bodyMedium.copy(fontSize = 22.sp, textAlign = TextAlign.Start)
            )
        }
    }
}

@Composable
fun OnboardNameDialog(
    modifier: Modifier = Modifier, onConfirm: (String) -> Unit, error: String?
) {
    var text by rememberSaveable { mutableStateOf("") }

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = modifier.wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(0.dp),
            colors = CardColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.White
            ),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.onboard_name_content),
                    modifier = modifier,
                    textAlign = TextAlign.Center,
                    style = Typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                )
                error?.let {
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = error,
                        modifier = modifier,
                        textAlign = TextAlign.Center,
                        style = Typography.bodyMedium.copy(
                            color = Red,
                            fontSize = 16.sp,
                        )
                    )
                }
                Spacer(modifier = modifier.height(36.dp))
                Column(modifier = modifier.padding(horizontal = 36.dp)) {
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = text,
                        onValueChange = { text = it },
                        textStyle = Typography.bodyMedium.copy(
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                        ),
                        decorationBox = { innerTextField ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                innerTextField()
                                Spacer(modifier = modifier.height(8.dp))
                                Canvas(
                                    Modifier.fillMaxWidth()
                                ) {
                                    drawLine(
                                        color = Color.White,
                                        strokeWidth = 5f,
                                        start = Offset(20f, 0f),
                                        end = Offset(size.width - 20, 0f),
                                        pathEffect = PathEffect.dashPathEffect(
                                            floatArrayOf(20f, 10f)
                                        )
                                    )
                                }
                            }
                        },
                    )
                    Spacer(modifier = modifier.height(36.dp))
                    ElevatedButton(
                        onClick = {
                            onConfirm(text)
                        },
                        colors = ButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = stringResource(R.string.common_ok),
                            style = Typography.bodyMedium.copy(
                                fontSize = 20.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardGenderDialog(
    modifier: Modifier = Modifier, onConfirm: (Int) -> Unit
) {
    var selectedGender by rememberSaveable { mutableStateOf(0) }

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = modifier.wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(0.dp),
            colors = CardColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.White
            ),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.onboard_gender_content),
                    modifier = modifier,
                    textAlign = TextAlign.Center,
                    style = Typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                )
                Spacer(modifier = modifier.height(36.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = modifier.width(20.dp))
                    for (i in 0..3) {
                        Image(
                            modifier = modifier
                                .weight(1f)
                                .alpha(if (selectedGender == i) 1f else 0.3f)
                                .clickable {
                                    selectedGender = i
                                },
                            painter = painterResource(
                                id = listOf(
                                    R.drawable.img_character1,
                                    R.drawable.img_character2,
                                    R.drawable.img_character3,
                                    R.drawable.img_character4
                                )[i],
                            ),
                            contentDescription = "character $i",
                        )
                        Spacer(modifier = modifier.width(20.dp))
                    }
                }
                Column(modifier = modifier.padding(horizontal = 36.dp)) {
                    Spacer(modifier = modifier.height(36.dp))
                    ElevatedButton(
                        onClick = {
                            onConfirm(selectedGender)
                        },
                        colors = ButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = stringResource(R.string.common_ok),
                            style = Typography.bodyMedium.copy(
                                fontSize = 20.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardWarningDialog(
    modifier: Modifier = Modifier, currentPage: Int, onConfirm: (Boolean) -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.White
            ),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = onboardWarningResources[currentPage],
                    modifier = modifier,
                    textAlign = TextAlign.Start,
                    style = Typography.bodyMedium.copy(
                        color = Color.White, fontSize = 18.sp, lineHeight = 28.sp
                    )
                )
                Spacer(modifier = modifier.height(36.dp))
                listOf(
                    stringResource(R.string.onboard_warning_answer_yes),
                    stringResource(R.string.onboard_warning_answer_no)
                ).zip(listOf(true, false)).forEach { (text, value) ->
                    ElevatedButton(
                        onClick = {
                            onConfirm(value)
                        },
                        colors = ButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Text(
                            modifier = modifier, text = text, style = Typography.bodyMedium.copy(
                                fontSize = 20.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardStatDialog(
    modifier: Modifier = Modifier, currentPage: Int, onConfirm: (Int) -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.White
            ),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = onboardStatResources[currentPage],
                    modifier = modifier,
                    textAlign = TextAlign.Start,
                    style = Typography.bodyMedium.copy(
                        color = Color.White, fontSize = 18.sp, lineHeight = 28.sp
                    )
                )
                Spacer(modifier = modifier.height(36.dp))
                listOf(
                    stringResource(R.string.onboard_stat_answer_1),
                    stringResource(R.string.onboard_stat_answer_2),
                    stringResource(R.string.onboard_stat_answer_3),
                    stringResource(R.string.onboard_stat_answer_4),
                    stringResource(R.string.onboard_stat_answer_5)
                ).forEachIndexed { i, text ->
                    ElevatedButton(
                        onClick = {
                            onConfirm(i)
                        },
                        colors = ButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Text(
                            modifier = modifier, text = text, style = Typography.bodyMedium.copy(
                                fontSize = 20.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}