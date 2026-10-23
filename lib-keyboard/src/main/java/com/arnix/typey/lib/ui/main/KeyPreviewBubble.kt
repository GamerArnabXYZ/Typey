package com.arnix.typey.lib.ui.main

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.arnix.typey.lib.ui.theme.TypeyLibKeyboardTheme
import com.arnix.typey.lib.ui.theme.KeypadDark
import com.arnix.typey.lib.ui.theme.KeypadLight

/**
 * Modern Jetpack Compose Key Preview Bubble component replacing legacy item_keyboard_main.xml.
 * Renders a floating elevated tactile preview above the pressed key.
 */
@Composable
fun KeyPreviewBubble(
    label: String,
    modifier: Modifier = Modifier,
    icon: Drawable? = null,
    isDarkTheme: Boolean = false
) {
    val backgroundColor = if (isDarkTheme) KeypadDark else KeypadLight
    val contentColor = if (isDarkTheme) Color.White else Color(0xFF1F1F1F)

    Surface(
        modifier = modifier
            .wrapContentSize()
            .defaultMinSize(minWidth = 48.dp, minHeight = 56.dp),
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        shadowElevation = 6.dp,
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                AndroidView(
                    modifier = Modifier.size(28.dp),
                    factory = { context ->
                        ImageView(context).apply {
                            setImageDrawable(icon)
                            scaleType = ImageView.ScaleType.FIT_CENTER
                        }
                    },
                    update = { imageView ->
                        imageView.setImageDrawable(icon)
                    }
                )
            } else {
                Text(
                    text = label,
                    fontSize = if (label.length > 2) 16.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(name = "Key Preview Light", showBackground = true)
@Composable
private fun KeyPreviewBubbleLightPreview() {
    TypeyLibKeyboardTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            KeyPreviewBubble(label = "A", isDarkTheme = false)
        }
    }
}

@Preview(name = "Key Preview Dark", showBackground = true, backgroundColor = 0xFF121212)
@Composable
private fun KeyPreviewBubbleDarkPreview() {
    TypeyLibKeyboardTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            KeyPreviewBubble(label = "K", isDarkTheme = true)
        }
    }
}
