package edu.danieljanis.shoppingmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.danieljanis.shoppingmvvm.database.ItemEntity
import edu.danieljanis.shoppingmvvm.database.ListRepository
import edu.danieljanis.shoppingmvvm.database.TitleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ListRepository
): ViewModel() {
    // Tells Kotlin to start new Coroutine in the Main thread context (Main safety with Room)

    // Repository I/O for titles
    fun insertTitle(title: TitleEntity) = GlobalScope.launch {
        repository.insertTitle(title)
    }

    fun getEveryTitle() = repository.getEveryTitle()

    fun editTitle(title: String, id: Int) = viewModelScope.launch {
        repository.editTitle(title, id)
    }

    fun removeTitle(id: Int) = GlobalScope.launch {
        repository.removeTitle(id)
    }

    // Repository I/O for items
    fun insertItem(item: ItemEntity) = GlobalScope.launch {
        repository.insertItem(item)
    }

    fun getEveryItem(listId: Int) = repository.getEveryItem(listId)

    fun editItem(name: String, cost: Double, amount: Int, itemId: Int, listId: Int) = viewModelScope.launch {
        repository.editItem(name, cost, amount, itemId, listId)
    }

    fun removeItem(itemId: Int) = GlobalScope.launch {
        repository.removeItem(itemId)
    }

    fun updateCheckbox(checked: Boolean, itemId: Int) = GlobalScope.launch {
        repository.updateCheckbox(checked, itemId)
    }
}