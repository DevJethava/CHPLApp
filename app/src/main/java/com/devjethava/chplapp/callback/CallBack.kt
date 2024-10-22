package com.devjethava.chplapp.callback

interface CallBack<T> {
    fun onSuccess(item: T?)
    fun onFailure()
}