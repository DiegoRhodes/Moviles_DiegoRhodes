package com.example.tienda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.repository.CategoryRepository
import com.example.tienda.states.CategoryState
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val state = CategoryState(CategoryRepository())

    val isLoading get() = state.isLoading

    val errorMessage get() = state.errorMessage

    val categories get() = state.categories

    fun loadCategories() {
        viewModelScope.launch {
            state.loadCategories()
        }
    }
}