package com.shub39.grit.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shub39.grit.viewModel.HabitViewModel

@Composable
fun HabitsList(
    viewModel: HabitViewModel,
    paddingValue: PaddingValues,
) {
    val habits by viewModel.habits.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize()
            .animateContentSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(habits, key = { it.id }) { habit ->
            HabitCard(
                habit = habit,
                habitViewModel = viewModel
            )
        }
        item {
            Spacer(modifier = Modifier.padding(60.dp))
        }
    }
}