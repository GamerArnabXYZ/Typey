package com.arnix.typey.ui.theme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arnix.typey.common.base.BaseComposeActivity
import com.arnix.typey.model.KeyboardThemeModel
import com.frogobox.sdk.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeActivity : BaseComposeActivity() {

    private val viewModel: ThemeViewModel by viewModels()

    private var themeList by mutableStateOf<List<KeyboardThemeModel>>(emptyList())
    private var activeThemeVersion by mutableIntStateOf(0)

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        viewModel.keyboardThemeState.observe(this) {
            themeList = it
            activeThemeVersion++
        }
        viewModel.getThemeData()
    }

    @Composable
    override fun Content() {
        val version = activeThemeVersion
        ThemeScreen(
            themeList = themeList,
            checkIsActive = { theme ->
                version >= 0 && viewModel.isThemeActive(theme)
            },
            onApplyTheme = { theme ->
                viewModel.setThemeColor(theme) {
                    activeThemeVersion++
                    viewModel.getThemeData()
                    showToast("${theme.name} Theme Applied")
                }
            },
            onBackClick = { finish() }
        )
    }
}