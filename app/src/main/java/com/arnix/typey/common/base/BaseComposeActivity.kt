package com.arnix.typey.common.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.arnix.typey.BuildConfig
import com.arnix.typey.ui.theme.compose.TypeyKeyboardTheme
import com.frogobox.sdk.view.FrogoActivity

/**
 * BaseComposeActivity provides a clean, unified base for Jetpack Compose screens
 * while maintaining compatibility with Frogo SDK's toolbar & debug setup.
 */
abstract class BaseComposeActivity : FrogoActivity() {

    override fun setupDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setContent {
            TypeyKeyboardTheme {
                Content()
            }
        }
    }

    @Composable
    abstract fun Content()
}
