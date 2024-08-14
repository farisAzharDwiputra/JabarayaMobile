package com.example.projectcheva.fiturUtama

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TravelPlanViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TravelPlanViewModel::class.java)) {
            return TravelPlanViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}