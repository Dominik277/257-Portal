package Portal.database

import Portal.database.dao.ObavijestiDao
import Portal.database.dao.SportDao
import Portal.database.dao.ZabavaDao
import Portal.database.table.ObavijestiTable
import Portal.database.table.SportTable
import Portal.database.table.ZabavaTable
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ZabavaTable::class,SportTable::class,ObavijestiTable::class],version = 1)
abstract class Portal257Database: RoomDatabase() {

    abstract fun zabavaDao(): ZabavaDao
    abstract fun sportDao(): SportDao
    abstract fun obavijestiDao(): ObavijestiDao

    companion object{
        @Volatile
        private var INSTANCE: Portal257Database? = null

        fun getDatabase(context: Context): Portal257Database{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Portal257Database::class.java,
                    "nk_jaksic_baza"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}