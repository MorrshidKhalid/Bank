package org.example

import org.example.ui.login.LoginScreen

fun main() {

    while (true)
        if (!LoginScreen.showLoginScreen())
            break


}