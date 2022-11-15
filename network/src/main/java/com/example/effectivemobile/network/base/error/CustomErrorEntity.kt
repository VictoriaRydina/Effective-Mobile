package com.example.effectivemobile.network.base.error

import okhttp3.ResponseBody

abstract class CustomErrorEntity(
    val errorBody: ResponseBody?,
    errorMessage: String): ErrorEntity(errorMessage)