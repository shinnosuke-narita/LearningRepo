package com.example.project

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.project.presentation.index.IndexScreen

@Composable
internal fun MainScreen() {
    Navigator(IndexScreen())
}
