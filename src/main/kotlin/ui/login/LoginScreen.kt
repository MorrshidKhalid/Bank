package org.example.ui.login

import org.example.CurrentUser
import org.example.objects.User
import org.example.ui.Screen
import org.example.ui.main.MainMenu


class LoginScreen : Screen() {


    companion object {

        private fun login(): Boolean {

            var loginField = false
            var loginAttempts = 0

            do {

                if (loginField) {
                    println("Invalid Username/Password\n")
                    loginAttempts += 1
                    println("You Have ${3 - loginAttempts} Trials to login\n")
                }


                if (loginAttempts == 3) { // This will exit of login screen.
                    println("You are Locked after 3 trails")
                    return false
                }

                print("Username: ")
                val userName = readln()
                print("Password: ")
                val userPassword = readln()

                CurrentUser.user = User.find(userName, userPassword)
                loginField = CurrentUser.user.isEmpty()

            }while (loginField)

            User.addNewRegister() // Here add record User logged in with data and time.
            MainMenu.showMainMenu()
            return true
        }

        fun showLoginScreen(msg: String = "Login Page"): Boolean {

            drawScreenHeader(msg)
            return login()
        }

    }

}