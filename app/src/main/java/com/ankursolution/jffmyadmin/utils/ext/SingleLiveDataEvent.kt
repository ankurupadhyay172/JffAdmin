package com.ankursolution.jffmyadmin.utils.ext

open class SingleLiveDataEvent<out T>(private val content:T) {

    private var hasBeenHandled = false

    /*
    Returns the content and prevents its use again.
    */


    fun getContentIfNotHandled():T?{
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun value() = getContentIfNotHandled()


/*
    Returns the content, even if it's already been handled
*/
    fun peekContent():T = content

}