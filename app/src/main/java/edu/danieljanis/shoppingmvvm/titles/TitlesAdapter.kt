package edu.danieljanis.shoppingmvvm.titles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.danieljanis.shoppingmvvm.ListViewModel
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.TitleEntity
import edu.danieljanis.shoppingmvvm.popups.AddTitlePopup
import edu.danieljanis.shoppingmvvm.popups.AddTitlePopupListener
import edu.danieljanis.shoppingmvvm.popups.EditTitlePopup
import edu.danieljanis.shoppingmvvm.popups.EditTitlePopupListener

class TitlesAdapter(
    var titles: List<TitleEntity>,
    private var clickListener: OnTitleClickListener,
    private val viewModel: ListViewModel
): RecyclerView.Adapter<TitlesAdapter.TitleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycled_titles, parent, false)
        return TitleHolder(view)
    }

    override fun getItemCount() = titles.size

    override fun onBindViewHolder(holder: TitleHolder, position: Int) {
        holder.bind(position)
        holder.initialize(titles[position], clickListener)
    }

    inner class TitleHolder(titleView: View): RecyclerView.ViewHolder(titleView) {
        private val title: TextView = titleView.findViewById(R.id.recycledTitle)
        private val editTitleBtn: ImageButton = titleView.findViewById(R.id.editButton)
        private val delTitleBtn: ImageButton = titleView.findViewById(R.id.deleteButton)

        fun bind(position: Int) {
            val currentTitle = titles[position]
            val titleId = currentTitle.id
            title.text = currentTitle.title
            editTitleBtn.setOnClickListener {
                EditTitlePopup(this.title.context,
                    object: EditTitlePopupListener {
                        override fun onEditButtonClicked(item: TitleEntity) {
                            viewModel.editTitle(item.title, titleId)
                            notifyDataSetChanged()
                        }
                    }).show()
            }
            delTitleBtn.setOnClickListener {
                viewModel.removeTitle(currentTitle.id)
            }
        }

        fun initialize(tempTitle: TitleEntity, action: OnTitleClickListener) {
            title.text = tempTitle.title

            itemView.setOnClickListener {
                action.onTitleClick(tempTitle, adapterPosition)
            }
        }
    }
}

interface OnTitleClickListener {
    fun onTitleClick(title: TitleEntity, position: Int)
}