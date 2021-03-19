package edu.danieljanis.shoppingmvvm.popups

import edu.danieljanis.shoppingmvvm.database.TitleEntity

interface EditTitlePopupListener {
    fun onEditButtonClicked(item: TitleEntity)
}