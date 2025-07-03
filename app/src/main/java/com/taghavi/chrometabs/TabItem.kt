package com.taghavi.chrometabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit,
)
