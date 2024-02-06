package com.sunshine.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sunshine.android.R

// Set of Material typography styles to start with
val galmuri = FontFamily(
    Font(R.font.galmuri7, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.galmuri11_bold, FontWeight.Bold, FontStyle.Normal)

)

val Typography = Typography(
    titleLarge  = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall= TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyLarge= TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    bodyMedium= TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)