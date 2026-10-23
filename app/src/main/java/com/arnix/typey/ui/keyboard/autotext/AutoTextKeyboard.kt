package com.arnix.typey.ui.keyboard.autotext

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.inputmethod.InputConnection
import android.widget.FrameLayout
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.arnix.typey.model.AutoTextEntity
import com.arnix.typey.ui.autotext.AutoTextActivity
import com.arnix.typey.ui.theme.compose.TypeyKeyboardTheme
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Modern Jetpack Compose-based AutoText Keyboard panel.
 */
class AutoTextKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val viewModel by lazy { AutoTextKeyboardViewModel(context) }
    private val autoTextState = MutableStateFlow<List<AutoTextEntity>>(emptyList())

    var currentInputConnection: InputConnection? = null
    var onBackClick: (() -> Unit)? = null

    private val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindowOrReleasedFromPool)
        setContent {
            TypeyKeyboardTheme {
                val list by autoTextState.collectAsState()
                AutoTextKeyboardScreen(
                    autoTextList = list,
                    onCommitText = { text ->
                        currentInputConnection?.commitText(text, 1)
                    },
                    onBackClick = {
                        onBackClick?.invoke()
                    },
                    onManageClick = {
                        val intent = Intent(context, AutoTextActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }

    init {
        addView(composeView)
        initData()
    }

    fun setInputConnection(inputConnection: InputConnection?) {
        currentInputConnection = inputConnection
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        onBackClick = listener
    }

    fun initData() {
        viewModel.getAutoText { items ->
            autoTextState.value = items
        }
    }

}