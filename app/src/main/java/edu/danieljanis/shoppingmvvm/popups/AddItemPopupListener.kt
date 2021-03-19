package edu.danieljanis.shoppingmvvm.popups

import edu.danieljanis.shoppingmvvm.database.ItemEntity

interface AddItemPopupListener {
    fun onAddButtonClicked(item: ItemEntity)
}