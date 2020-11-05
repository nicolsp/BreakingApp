package com.example.breakingapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.breakingapp.BreakingRepository
import com.example.breakingapp.local.BreakingDataBase
import com.example.breakingapp.local.RealBreaking

class BreakingViewModel(application: Application): AndroidViewModel(application) {
    private val mRepository : BreakingRepository
    val liveDataFromLocal : LiveData<List<RealBreaking>>


    init {
        val breDao = BreakingDataBase.getDataBase(application).breakingDao()
        mRepository = BreakingRepository(breDao)
        mRepository.getDataFromServe()

        liveDataFromLocal = mRepository.mLiveData
    }

    fun exposeLiveDataFromDataBase(): LiveData<List<RealBreaking>> {
        return mRepository.mLiveData
    }
    //obtainBreakingByID(id: Int): LiveData<Rea>

    fun obtainBreakingByID(id: Int): LiveData<RealBreaking> {
        return mRepository.obtainBreakingByID(id)
    }
}