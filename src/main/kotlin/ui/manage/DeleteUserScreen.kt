package org.example.ui.manage

import org.example.lib.Util.Companion.line
import org.example.objects.User
import org.example.ui.Screen

class DeleteUserScreen : Screen() {

    companion object {

        private const val TITLE = "Delete User Screen"

        private fun printUser(user: User) {

            println("\nUser Card")
            line()
            println("Username        : ${user.userName}")
            println("Password        : ${user.password}")
            println("Permission num  : ${user.permission}")
            line()
        }

        fun showDeleteUserScreen() {
            drawScreenHeader(TITLE)

            print("Please Enter Username: ")
            var username = readln()

            while (!User.isUserExists(username)) {
                println("Username ($username) not Found, Please Chose another One: ")
                username = readln()
            }

            val userToDelete = User.find(username)
            printUser(userToDelete)

            print("Are you sure you want to delete this user y/n? ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                if (userToDelete.delete()) {
                    println("User deleted Successfully :)")
                    printUser(userToDelete)
                } else println("Error can't delete this user!")
            } else println("Error User was not deleted :(")
        }
    }
}