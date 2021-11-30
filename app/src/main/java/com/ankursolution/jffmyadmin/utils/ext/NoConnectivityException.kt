package com.ankursolution.jffmyadmin.utils.ext

import java.io.IOException

class NoConnectivityException:IOException() {
    override val message:String
    get() = "No internet found. Check your connection or try again"

}