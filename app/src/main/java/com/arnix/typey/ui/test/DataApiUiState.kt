package com.arnix.typey.ui.test

import androidx.annotation.Keep
import com.arnix.typey.data.remote.model.DataItemResponse

/**
 * UI State machine representing Remote API fetch lifecycle in Test Playground.
 */
@Keep
sealed interface DataApiUiState {
    data object Idle : DataApiUiState
    data object Loading : DataApiUiState
    data class Success(
        val total: Int,
        val lastUpdatedWib: String,
        val items: List<DataItemResponse>
    ) : DataApiUiState
    data class Error(val message: String) : DataApiUiState
}
