package edu.danieljanis.shoppingmvvm.popups

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.TitleEntity
import kotlinx.android.synthetic.main.add_list_title.*

class AddTitlePopup(context: Context, var addTitlePopupListener: AddTitlePopupListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_list_title)

        addTitleBtn.setOnClickListener {
            val title = editTitle.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(context, "No title added :(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tempTitle = TitleEntity(title = title)
            addTitlePopupListener.onAddButtonClicked(tempTitle)
            dismiss()
        }
        cancelTitleBtn.setOnClickListener {
            cancel()
        }
    }
}