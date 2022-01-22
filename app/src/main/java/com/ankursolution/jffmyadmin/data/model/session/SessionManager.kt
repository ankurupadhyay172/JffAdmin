package com.ankursolution.jffmyadmin.data.model.session

import com.ankursolution.jffmyadmin.data.model.UserModel

interface SessionManager {
    fun saveUserAuth(user:UserModel.Result?)
    fun getUserAuth():UserModel.Result?
    fun deleteAuth()

}