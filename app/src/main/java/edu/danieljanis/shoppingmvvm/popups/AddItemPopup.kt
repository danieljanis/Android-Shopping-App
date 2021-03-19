package edu.danieljanis.shoppingmvvm.popups

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.ItemEntity
import kotlinx.android.synthetic.main.add_list_item.*

class AddItemPopup(context: Context, private val listId: Int, private var addItemPopupListener: AddItemPopupListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_list_item)

        addItemBtn.setOnClickListener {
            val itemName = editName.text.toString()
            val itemCost = editCost.text
            val itemAmount = editAmount.text

            if (itemName.isEmpty() || itemCost.isEmpty() || itemAmount.isEmpty()) {
                Toast.makeText(context, "Not enough information :(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tempItem = ItemEntity(itemId = 0, name = itemName, cost = itemCost.toString().toDouble(), amount = itemAmount.toString().toInt(), listId = listId, checked = false)
            addItemPopupListener.onAddButtonClicked(tempItem)
            dismiss()
        }
        cancelItemBtn.setOnClickListener {
            cancel()
        }
    }
}