package com.arnix.typey.util

import android.view.ViewGroup
import com.arnix.typey.lib.common.core.BaseKeyboard

class KeyboardNavigationHelper {
    companion object {
        fun navigateTo(baseKeyboard: BaseKeyboard<*>, destinationKeyboard: BaseKeyboard<*>) {
            baseKeyboard.parent?.let {
                if (it is ViewGroup) {
                    it.removeView(baseKeyboard)
                }
            }

            destinationKeyboard.parent?.let {
                if (it is ViewGroup) {
                    it.addView(destinationKeyboard)
                }
            }
        }
    }
}