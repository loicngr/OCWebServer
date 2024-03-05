package fr.loicnogier.utils

import fr.loicnogier.interfaces.ItemInterface

fun <T : ItemInterface> findById(storage: MutableList<T>, id: Int): T? = storage.find { it.id == id }

fun <T : ItemInterface> findIndex(storage: MutableList<T>, item: T): Int? {
    val index = storage.indexOf(item)
    return if (index == -1) null else index
}

fun <T : ItemInterface> findIndexById(storage: MutableList<T>, id: Int): Int? {
    val foundItem = findById<T>(storage, id) ?: return null
    val index = storage.indexOf(foundItem)
    return if (index == -1) null else index
}

fun <T : ItemInterface> deleteByIndex(storage: MutableList<T>, index: Int): T = storage.removeAt(index)
