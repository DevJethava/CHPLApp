package com.devjethava.chplapp.callback

interface CallBackWithMessage<T> {
    fun onSuccess(item: T?)
    fun onFailure(message: String?)
}