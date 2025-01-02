package com.shub39.grit.core.presentation.settings

import androidx.compose.runtime.Immutable
import com.shub39.grit.core.domain.DefaultPage
import com.shub39.grit.tasks.domain.Category
import com.shub39.grit.tasks.domain.ClearPreferences

@Immutable
data class SettingsState(
    val defaultPage: DefaultPage = DefaultPage.TASKS,
    val currentClearPreference: String = ClearPreferences.NEVER.value,
    val categories: List<Category> = emptyList()
)
