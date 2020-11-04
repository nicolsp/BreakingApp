package com.example.breakingapp.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BreakingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllBreaking(mList: List<RealBreaking>)

    @Query("SELECT* FROM breaking_table")
    fun showAllBreaking(): LiveData<List<RealBreaking>>

    @Query("SELECT*FROM breaking_table WHERE appearance =:mId")
    fun showOnBreakingByID(mId: Int): LiveData<RealBreaking>

    @Update
    suspend fun updateBreaking(realBreaking: RealBreaking)
}