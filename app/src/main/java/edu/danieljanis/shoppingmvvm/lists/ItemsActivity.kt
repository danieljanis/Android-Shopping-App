package edu.danieljanis.shoppingmvvm.lists

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.ItemEntity
import edu.danieljanis.shoppingmvvm.popups.AddItemPopup
import edu.danieljanis.shoppingmvvm.popups.AddItemPopupListener
import edu.danieljanis.shoppingmvvm.ListViewModel
import edu.danieljanis.shoppingmvvm.ListViewModelFactory
import edu.danieljanis.shoppingmvvm.util.DoubleConvertUtil
import kotlinx.android.synthetic.main.activity_items.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ItemsActivity: AppCompatActivity(), KodeinAware, OnItemClickListener {
    override val kodein by kodein()
    private val factory: ListViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        val adapter = ItemsAdapter(listOf(), this, viewModel)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val listId = intent.getIntExtra("listId", 0)

        viewModel.getEveryItem(listId).observe(this, Observer {
            adapter.items = it
            showTotalCost(it)
            adapter.notifyDataSetChanged()
        })

        f_a_b2.setOnClickListener {
            AddItemPopup(this,
                listId,
            object: AddItemPopupListener {
                override fun onAddButtonClicked(item: ItemEntity) {
                    viewModel.insertItem(item)
                }
            }).show()
        }
    }

    private fun showTotalCost(items: List<ItemEntity>) {
        val totalCost = items.map { it.cost * it.amount }.sum()
        totalCostTextView.text = DoubleConvertUtil.convertDouble(totalCost)
    }

    override fun onItemClick(item: ItemEntity, position: Int) {
        Log.e("ItemsActivity", "clicked a item at $position")
    }
}