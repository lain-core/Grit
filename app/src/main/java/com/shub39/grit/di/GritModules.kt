package com.shub39.grit.di

import com.shub39.grit.database.habit.HabitDatabase
import com.shub39.grit.database.task.TaskDatabase
import com.shub39.grit.notification.NotificationAlarmScheduler
import com.shub39.grit.viewModel.HabitViewModel
import com.shub39.grit.viewModel.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HabitDatabase.getDatabase(get()) }
    single { TaskDatabase.getDatabase(get()) }
    single { NotificationAlarmScheduler(get()) }
    viewModel { HabitViewModel(get(), get()) }
    viewModel { TaskListViewModel(get(), get()) }
}