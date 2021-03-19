package edu.danieljanis.shoppingmvvm.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.ItemEntity
import edu.danieljanis.shoppingmvvm.ListViewModel
import edu.danieljanis.shoppingmvvm.popups.EditItemPopup
import edu.danieljanis.shoppingmvvm.popups.EditItemPopupListener
import edu.danieljanis.shoppingmvvm.util.DoubleConvertUtil

class ItemsAdapter(
    var items: List<ItemEntity>,
    var clickListener: OnItemClickListener,
    private val viewModel: ListViewModel
): RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycled_items, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position], position)
        holder.initialize(items[position], clickListener)
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.recycledItem)
        private val itemCost: TextView = itemView.findViewById(R.id.recycledItemCost)
        private val itemAmount: TextView = itemView.findViewById(R.id.recycledItemAmount)
        private val checked: Switch = itemView.findViewById(R.id.itemSwitch)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton2)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton2)

        fun bind(tempItem: ItemEntity, position: Int) {

            val currentItem = items[position]
            val itemId = currentItem.itemId
            val listId = currentItem.listId
            itemName.text = currentItem.name
            itemCost.text = DoubleConvertUtil.convertDouble(currentItem.cost)
            itemAmount.text = currentItem.amount.toString()

            checked.setOnClickListener {
                viewModel.updateCheckbox(!currentItem.checked, currentItem.itemId)
                notifyDataSetChanged()
            }

            checked.isChecked = tempItem.checked

            editButton.setOnClickListener {
                EditItemPopup(this.itemName.context,
                object: EditItemPopupListener {
                    override fun onEditButtonClicked(item: ItemEntity) {
                        viewModel.editItem(item.name, item.cost, item.amount, itemId, listId)
                        notifyDataSetChanged()
                    }
                }).show()
            }

            deleteButton.setOnClickListener {
                viewModel.removeItem(currentItem.itemId)
            }
        }

        fun initialize(tempItem: ItemEntity, action: OnItemClickListener) {
            itemName.text = tempItem.name
            itemCost.text = DoubleConvertUtil.convertDouble(tempItem.cost)
            itemAmount.text = tempItem.amount.toString()
            tempItem.itemId

            itemView.setOnClickListener {
                action.onItemClick(tempItem, adapterPosition)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(item: ItemEntity, position: Int)
}