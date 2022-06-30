package com.example.pictureofthedaymy.viewmodel

import com.example.pictureofthedaymy.model.PictureOfTheDayResponseData

sealed class PictureOfTheDayAppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) :
        PictureOfTheDayAppState()

    data class Error(val error: Throwable) : PictureOfTheDayAppState()

    data class Loading(val progress: Int?) :
        PictureOfTheDayAppState()
}
