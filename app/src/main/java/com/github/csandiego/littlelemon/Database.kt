package com.github.csandiego.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {

    @Query("SELECT * FROM MenuItem")
    fun getAll(): LiveData<List<MenuItem>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg item: MenuItem)
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun menuItemDao(): MenuItemDao

    companion object {

        private var database: MenuDatabase? = null

        fun getInstance(context: Context): MenuDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    MenuDatabase::class.java,
                    "menu.db"
                ).build()
            }
            return database!!
        }
    }
}