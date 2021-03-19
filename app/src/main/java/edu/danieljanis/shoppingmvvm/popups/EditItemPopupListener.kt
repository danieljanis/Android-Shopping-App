package edu.danieljanis.shoppingmvvm.popups

import edu.danieljanis.shoppingmvvm.database.ItemEntity

interface EditItemPopupListener {
    fun onEditButtonClicked(item: ItemEntity)
}