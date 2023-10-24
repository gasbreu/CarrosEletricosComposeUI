package com.example.carroseletricos.data

import androidx.compose.runtime.Composable

class TabRowItem (
    val title: String,
    val screen: @Composable () -> Unit
)