package com.example.librarymanager

import android.app.Application
import com.example.librarymanager.data.ItemRoomDatabase

class LibraryApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}