package com.example.breakingapp.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breaking_table")
data class RealBreaking(@PrimaryKey @NonNull val appearance: List<Int>,
                        val birthday: String,
                        val category: String,
                        val charId: Int,
                        val img: String,
                        val name: String,
                        val nickname: String,
                        val portrayed: String,
                        val status: String

                        )
