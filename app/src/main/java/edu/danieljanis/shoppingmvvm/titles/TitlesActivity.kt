package edu.danieljanis.shoppingmvvm.titles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.danieljanis.shoppingmvvm.ListViewModel
import edu.danieljanis.shoppingmvvm.ListViewModelFactory
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.TitleEntity
import edu.danieljanis.shoppingmvvm.lists.ItemsActivity
import edu.danieljanis.shoppingmvvm.popups.AddTitlePopup
import edu.danieljanis.shoppingmvvm.popups.AddTitlePopupListener
import kotlinx.android.synthetic.main.activity_titles.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TitlesActivity: AppCompatActivity(), KodeinAware, OnTitleClickListener {
    override val kodein by kodein()
    private val factory: ListViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_titles)

        val viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        val adapter = TitlesAdapter(listOf(), this, viewModel)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTitles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.getEveryTitle().observe(this, Observer {
            adapter.titles = it
            adapter.notifyDataSetChanged()
        })

        f_a_b.setOnClickListener {
            AddTitlePopup(this,
            object: AddTitlePopupListener {
                override fun onAddButtonClicked(item: TitleEntity) {
                    viewModel.insertTitle(item)
                }
            }).show()
        }
    }

    override fun onTitleClick(title: TitleEntity, position: Int) {
        // listId is passed to the ItemsActivity (so that each list is tied to the proper title)
        Log.e("TitlesActivity", "clicked a title at $position")
        val intent = Intent(this, ItemsActivity::class.java)
        intent.putExtra("listId", title.id)
        startActivity(intent)
    }
}