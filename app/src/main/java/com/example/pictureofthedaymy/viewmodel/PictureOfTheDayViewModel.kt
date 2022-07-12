package com.example.pictureofthedaymy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureofthedaymy.BuildConfig
import com.example.pictureofthedaymy.model.PictureOfTheDayResponseData
import com.example.pictureofthedaymy.model.RepositoryImpl
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayAppState.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayAppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<PictureOfTheDayAppState> {
        return liveData
    }

    fun sendServerRequest() {
        liveData.value = Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = Error(Throwable("wrong key"))
        } else {
            repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(apiKey, date = String()).enqueue(callback)
        }
    }

    fun sendServerRequest(date: String) {
        liveData.value = Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = Error(Throwable("wrong key"))
        } else {
            repositoryImpl.getPictureOfTheDayApi().getPictureOfTheDay(apiKey, date).enqueue(callback)
        }
    }

    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(Success(response.body()!!))
            } else {
                liveData.postValue(Error(throw IllegalStateException("Проблема")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            // TODO HW
        }
    }
}