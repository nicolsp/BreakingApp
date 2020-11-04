package com.example.breakingapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RealBreaking::class],version = 1)

abstract class BreakingDataBase(): RoomDatabase() {
    abstract fun breakingDao(): BreakingDao


    companion object  {
        @Volatile
        private var INSTANCE : BreakingDataBase? = null
        fun getDataBase(context: Context): BreakingDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                BreakingDataBase::class.java,"breakingDb")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}