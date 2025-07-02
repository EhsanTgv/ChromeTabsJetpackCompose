package com.taghavi.chrometabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tab(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    cornerShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    onClose: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                color = containerColor,
                shape = TabShape(cornerShape),
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = title,
                maxLines = 1,
                fontSize = 16.sp,
            )
            IconButton(
                onClick = onClose
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close tab",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TabPreview() {
    Tab(
        title = "Home",
        icon = Icons.Default.Home,
        onClose = {},
        onClick = {},
    )
}