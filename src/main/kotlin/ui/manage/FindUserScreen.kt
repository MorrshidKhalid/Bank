package org.example.ui.manage

import org.example.lib.Util
import org.example.objects.User
import org.example.ui.Screen

class FindUserScreen : Screen() {

    companion object {

        private const val TITLE = "Find User Screen"

        private fun printUser(user: User) {

            println("\nUser Card")
            Util.line()
            println("Username        : ${user.userName}")
            println("Password        : ${user.password}")
            println("Permission num  : ${user.permission}")
            Util.line()
        }

        fun showFindUserScreen() {
            drawScreenHeader(TITLE)

            print("Please Enter Username: ")
            var username = readln()

            while (!User.isUserExists(username)) {
                println("Username ($username) not Found, Please Chose another One: ")
                username = readln()
            }

            printUser(User.find(username))
        }
    }
}