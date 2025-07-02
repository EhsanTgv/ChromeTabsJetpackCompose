package com.taghavi.chrometabs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TabShape(
    private val cornerRadius: RoundedCornerShape,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val topStart = cornerRadius.topStart.toPx(size, density)
        val topEnd = cornerRadius.topEnd.toPx(size, density)
        val bottomEnd = cornerRadius.bottomEnd.toPx(size, density)
        val bottomStart = cornerRadius.bottomStart.toPx(size, density)

        if (topStart + topEnd + bottomEnd + bottomStart == 0f) {
            return Outline.Rectangle(size.toRect())
        }

        val topLeft = topStart
        val topRight = topEnd
        val bottomRight = bottomEnd
        val bottomLeft = bottomStart

        val (width, height) = size

        val tabPath = Path().apply {
            moveTo(x = topLeft, y = 0f)

            lineTo(x = width - topRight, 0f)

            quadraticTo(
                x1 = width, y1 = 0f,
                x2 = width, y2 = topRight
            )

            lineTo(x = width, y = height - bottomRight)

            quadraticTo(
                x1 = width, y1 = height,
                x2 = width + bottomRight, y2 = height
            )

            lineTo(x = -bottomLeft, height)

            quadraticTo(
                x1 = 0f, y1 = height,
                x2 = 0f, y2 = height - bottomLeft
            )

            lineTo(x = 0f, topLeft)

            quadraticTo(
                x1 = 0f, y1 = 0f,
                x2 = topLeft, y2 = 0f
            )

            close()
        }

        return Outline.Generic(tabPath)
    }
}