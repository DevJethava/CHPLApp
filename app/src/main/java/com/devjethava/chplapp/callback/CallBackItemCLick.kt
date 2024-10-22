package com.devjethava.chplapp.callback

interface CallBackItemCLick<T> {
    fun onItemClick(item: T?, position: Int, message: String)
}