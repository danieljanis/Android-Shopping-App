package edu.danieljanis.shoppingmvvm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "TitleTable")
class TitleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    var title: String
)

@Entity(tableName = "ItemTable",
    foreignKeys = [ForeignKey(entity = TitleEntity::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("listId"), onDelete = ForeignKey.CASCADE)])
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var cost: Double,
    @ColumnInfo
    var amount: Int,
    @ColumnInfo(name = "listId")
    val listId: Int,
    @ColumnInfo
    var checked: Boolean
)