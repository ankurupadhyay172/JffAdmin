package com.ankursolution.jffmyadmin.data.model.session

import android.content.Context
import android.content.SharedPreferences
import com.ankursolution.jffmyadmin.data.model.UserModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(@ApplicationContext context: Context):SessionManager {
    private val prefs:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)


    companion object{
        const val PREF_NAME = "user_session"
        const val USER_DATA = "user_data"
    }


    override fun saveUserAuth(user: UserModel.Result?) {
        val editor = prefs.edit()
        editor.putString(USER_DATA,Gson().toJson(user))
        editor.apply()
    }

    override fun getUserAuth(): UserModel.Result? {
        if (prefs.getString(USER_DATA,null)!=null)
        {
            var user =  Gson().fromJson(prefs.getString(USER_DATA,null), UserModel.Result::class.java)
            return user
        }else
            return null
    }

    override fun deleteAuth() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}