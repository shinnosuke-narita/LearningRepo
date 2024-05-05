package com.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.project.ui.index.IndexScreen

@Composable
internal fun MainScreen() {
    MaterialTheme {
        Navigator(IndexScreen())
    }
}
