package com.arnix.typey.ui.keyboard.autotext

import android.content.Context
import com.arnix.typey.data.local.db.AppDatabase
import com.arnix.typey.model.AutoTextEntity
import com.arnix.typey.repository.autotext.AutoTextRepository
import com.arnix.typey.repository.autotext.AutoTextRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Faisal Amir on 11/03/23
 * https://github.com/amirisback
 */

class AutoTextKeyboardViewModel(val context: Context) {

    private val repository: AutoTextRepository by lazy {
        AutoTextRepositoryImpl(AppDatabase.newInstance(context).autoTextDao())
    }

    fun getAutoTextFlow(): Flow<List<AutoTextEntity>> {
        return repository.getAutoText()
    }

    fun getAutoText(onSuccessData: (List<AutoTextEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getAutoText().collect { data ->
                    withContext(Dispatchers.Main) {
                        onSuccessData(data)
                    }
                }
            } catch (_: Exception) {
            }
        }
    }

}