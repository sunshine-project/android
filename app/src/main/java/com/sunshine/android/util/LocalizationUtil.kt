package com.sunshine.android.util

import com.sunshine.android.SunApplication

fun getString(resId: Int): String {
    return SunApplication.resourses.getString(resId)
}