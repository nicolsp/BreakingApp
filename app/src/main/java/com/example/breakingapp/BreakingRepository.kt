package com.example.breakingapp

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.breakingapp.local.BreakingDao
import com.example.breakingapp.local.RealBreaking
import com.example.breakingapp.network.RetrofitClient
import com.example.breakingapp.pojos.BreakingEntityItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreakingRepository(private val breakingDao: BreakingDao) {
    private val Retroservice = RetrofitClient.getRetrofitCliente()
    val mLiveData = breakingDao.showAllBreaking()

    fun obtainBreakingByID(id: Int): LiveData<RealBreaking> {
        return breakingDao.showOnBreakingByID(id)
    }

    fun getDataFromServe() {
        val call = Retroservice.fetAllBreaking()
        call.enqueue(object : Callback<List<BreakingEntityItem>>  {
            override fun onResponse(
                call: Call<List<BreakingEntityItem>>,
                response: Response<List<BreakingEntityItem>>
            ) {
                when(response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        Log.d("RESPONDEOK",response.code().toString())
                        response.body()?.let {
                            breakingDao.insertAllBreaking(convert(it))
                        }
                    }
                    in 300..509 -> Log.d("RESPONSE_300",response.body().toString())
                    else -> Log.d("ERROR",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<BreakingEntityItem>>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
            }


        })


    }

    fun convert(listFromNetwork: List<BreakingEntityItem>): List<RealBreaking> {
        val listmutable = mutableListOf<RealBreaking>()
        listFromNetwork.map {
            listmutable.add(
                RealBreaking(it.appearance,
                    it.birthday,
                    it.category,
                    it.charId,
                    it.img,
                    it.name,
                    it.nickname,
                    it.portrayed,
                    it.status


                    )
            )
        }
        return listmutable

}



}

private fun <T> Call<T>.enqueue(callback: Callback<List<T>>) {

}
