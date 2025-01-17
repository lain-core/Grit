package com.shub39.grit.tasks.data.repository

import com.shub39.grit.core.data.mappers.toCategory
import com.shub39.grit.core.data.mappers.toCategoryEntity
import com.shub39.grit.core.data.mappers.toTask
import com.shub39.grit.core.data.mappers.toTaskEntity
import com.shub39.grit.tasks.data.database.CategoryDao
import com.shub39.grit.tasks.data.database.TasksDao
import com.shub39.grit.tasks.domain.Category
import com.shub39.grit.tasks.domain.Task
import com.shub39.grit.tasks.domain.TaskRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class TasksRepository(
    private val tasksDao: TasksDao,
    private val categoryDao: CategoryDao
): TaskRepo {
    override fun getTasks(): Flow<Map<Category, List<Task>>> {
        val tasksFlow = tasksDao.getTasksFlow().map { entities ->
            entities.map { it.toTask() }
        }
        val categoriesFlow = categoryDao.getCategories().map { entities ->
            entities.map { it.toCategory() }
        }

        return tasksFlow.combine(categoriesFlow) { tasks, categories ->
            categories.associateWith { category ->
                tasks.filter { it.categoryId == category.id }
            }
        }
    }

    override suspend fun upsertTask(task: Task) {
        tasksDao.upsertTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        tasksDao.deleteTask(task.toTaskEntity())
    }

    override suspend fun upsertCategory(category: Category) {
        categoryDao.upsertCategory(category.toCategoryEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toCategoryEntity())
    }
}