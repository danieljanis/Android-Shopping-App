package edu.danieljanis.shoppingmvvm.database

class ListRepository(
    private val database: ListDatabase
) {
    // Functions for titles
    suspend fun insertTitle(title: TitleEntity) = database.getListDao().insertTitle(title)

    fun getEveryTitle() = database.getListDao().getEveryTitle()

    suspend fun editTitle(title: String, id: Int) = database.getListDao().editTitle(title, id)

    suspend fun removeTitle(id: Int) = database.getListDao().removeTitle(id)

    // Functions for items in the titles lists
    suspend fun insertItem(item: ItemEntity) = database.getListDao().insertItem(item)

    fun getEveryItem(listId: Int) = database.getListDao().getEveryItem(listId)

    suspend fun editItem(name: String, cost: Double, amount: Int, itemId: Int, listId: Int) = database
        .getListDao().editItem(name, cost, amount, itemId, listId)

    suspend fun removeItem(itemId: Int) = database.getListDao().removeItem(itemId)

    suspend fun updateCheckbox(checked: Boolean, itemId: Int) = database.getListDao().updateCheckbox(checked, itemId)
}