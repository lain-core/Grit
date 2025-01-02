package com.shub39.grit.core.presentation.settings

import com.shub39.grit.core.domain.DefaultPage
import com.shub39.grit.tasks.domain.Category

sealed interface SettingsAction {
    data class UpdateDefaultPage(val defaultPage: DefaultPage): SettingsAction
    data class UpdateClearPreference(val clearPreference: String): SettingsAction
    data class DeleteCategory(val category: Category): SettingsAction
}