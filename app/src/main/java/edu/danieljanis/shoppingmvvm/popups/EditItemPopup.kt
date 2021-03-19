package edu.danieljanis.shoppingmvvm.popups

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.ItemEntity
import kotlinx.android.synthetic.main.add_list_item.*
import kotlinx.android.synthetic.main.edit_list_item.*

class EditItemPopup(context: Context, var editItemPopupListener: EditItemPopupListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_list_item)

        editItemBtn.setOnClickListener {
            val itemName = editName2.text.toString()
            val itemCost = editCost2.text
            val itemAmount = editAmount2.text

            if (itemName.isEmpty() || itemCost.isEmpty() || itemAmount.isEmpty()) {
                Toast.makeText(context, "Not enough information :(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tempItem = ItemEntity(itemId = 0, name = itemName, cost = itemCost.toString().toDouble(), amount = itemAmount.toString().toInt(), listId = 0, checked = false)
            editItemPopupListener.onEditButtonClicked(tempItem)
            dismiss()
        }
        cancelItemBtn2.setOnClickListener {
            cancel()
        }
    }
}