package org.example.ui.login

import org.example.CurrentUser
import org.example.objects.User
import org.example.ui.Screen
import org.example.ui.main.MainMenu


class LoginScreen : Screen() {


    companion object {

        private fun login() {

            var loginField = false

            do {

                if (loginField)
                    println("Invalid Username/Password\n")

                print("Username: ")
                val userName = readln()
                print("Password: ")
                val userPassword = readln()

                CurrentUser.user = User.find(userName, userPassword)
                loginField = CurrentUser.user.isEmpty()

            }while (loginField)

            MainMenu.showMainMenu()
        }

        fun showLoginScreen(msg: String = "Login Page") {

            DrawScreenHeader(msg)
            login()
        }

    }

}