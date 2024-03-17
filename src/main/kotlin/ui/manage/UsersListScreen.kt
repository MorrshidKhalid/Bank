package org.example.ui.manage

import org.example.objects.User
import org.example.ui.Screen

class UsersListScreen : Screen() {

    companion object {

        private val _title = "User(s) List Screen (${User.getUserList().size})"

        private fun printLineUser(user: User) {
            println("%-16s|%-16s|%-16s".format(user.userName, user.password, user.permission.toString()))
        }

        fun showUsersListScreen() {
            DrawScreenHeader(_title)

            println("%-16s|%-16s|%-16s".format("Username", "Password", "Permission Number"))
            val usersList = User.getUserList()
            usersList.forEach {
                printLineUser(it)
            }
        }
    }

}