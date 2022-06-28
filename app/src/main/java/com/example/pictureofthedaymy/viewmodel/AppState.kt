package com.example.pictureofthedaymy.viewmodel

import com.example.pictureofthedaymy.model.PictureOfTheDayResponseData

sealed class AppState{
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):AppState()
    data class Error(val  error: Throwable): AppState()
    object Loading: AppState()
}
