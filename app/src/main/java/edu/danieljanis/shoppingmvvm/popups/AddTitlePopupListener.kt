package edu.danieljanis.shoppingmvvm.popups

import edu.danieljanis.shoppingmvvm.database.TitleEntity

interface AddTitlePopupListener {
    fun onAddButtonClicked(item: TitleEntity)
}