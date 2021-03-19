package edu.danieljanis.shoppingmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.danieljanis.shoppingmvvm.database.ListRepository

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(
    private val repository: ListRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}