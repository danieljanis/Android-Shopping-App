package edu.danieljanis.shoppingmvvm.popups

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import edu.danieljanis.shoppingmvvm.R
import edu.danieljanis.shoppingmvvm.database.TitleEntity
import kotlinx.android.synthetic.main.edit_list_title.*

class EditTitlePopup(context: Context, var editTitlePopupListener: EditTitlePopupListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_list_title)

        editTitleBtn.setOnClickListener {
            val title = editTitle2.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(context, "No title edited :(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tempTitle = TitleEntity(title = title)
            editTitlePopupListener.onEditButtonClicked(tempTitle)
            dismiss()
        }
        cancelTitleBtn2.setOnClickListener {
            cancel()
        }

    }
}