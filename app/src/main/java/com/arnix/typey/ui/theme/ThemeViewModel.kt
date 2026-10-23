package com.arnix.typey.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnix.typey.R
import com.arnix.typey.common.base.BaseViewModel
import com.arnix.typey.model.KeyboardThemeModel
import com.arnix.typey.model.ThemeType
import com.arnix.typey.services.KeyboardUtil
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by faisalamircs on 16/02/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val pref: PreferenceDelegates,
    private val keyboardUtil: KeyboardUtil
) : BaseViewModel() {

    private var _keyboardThemeState = MutableLiveData<List<KeyboardThemeModel>>()
    var keyboardThemeState: LiveData<List<KeyboardThemeModel>> = _keyboardThemeState

    fun getThemeData() {
        _keyboardThemeState.postValue(keyboardUtil.keyboardTheme())
    }

    fun getThemeColor(): Int {
        return pref.getPrefInt(KeyboardUtil.KEYBOARD_COLOR, R.color.color_bg_keyboard_default)
    }

    fun getThemeType(): String {
        return pref.getPrefString(KeyboardUtil.KEYBOARD_COLOR_TYPE, ThemeType.COLOR.name)
    }

    fun isThemeActive(data: KeyboardThemeModel): Boolean {
        return data.background == getThemeColor() && data.themType.name == getThemeType()
    }

    fun setThemeColor(data: KeyboardThemeModel, onSuccess: () -> Unit) {
        pref.savePrefString(KeyboardUtil.KEYBOARD_COLOR_TYPE, data.themType.name)
        pref.savePrefInt(KeyboardUtil.KEYBOARD_COLOR, data.background)
        onSuccess()
    }

}