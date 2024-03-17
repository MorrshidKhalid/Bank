package org.example.ui.manage

import org.example.SaveResult
import org.example.lib.Util
import org.example.objects.User
import org.example.ui.Screen
import org.example.ui.main.UpdateClientScreen

class UpdateUserScreen : Screen() {

    companion object {

        private const val TITLE = "Update User Screen"
        private const val ERROR_MSG = "Error user was not saved because username is used!"
        private const val EMPTY_MSG = "User was not saved because it's Empty"

        private fun printUser(user: User) {

            println("\nUser Card")
            Util.line()
            println("Username        : ${user.userName}")
            println("Password        : ${user.password}")
            println("Permission num  : ${user.permission}")
            Util.line()
        }

        private fun readPermissionsToSet(): Int {

            var permission = 0

            print("Do you want to give full access? y/n ")
            var ans = readln().lowercase()
            if (ans == "y")
                return User.Companion.Permissions.All.num


            println("Do you want to give to: ")
            print("Show Client list? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.ShowClientList.num

            print("Add New Client? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.AddClient.num


            print("Delete Client? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.DeleteClient.num


            print("Update Client? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.UpdateClient.num


            print("Find Client? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.FindClient.num


            print("Transactions? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.Transactions.num


            print("Manage Users? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.ManageUsers.num

            return permission
        }

        private fun readUserInfo(user: User) {

            print("Please Enter Password: ")
            user.password = readln()
            user.permission = readPermissionsToSet()
        }

        fun showUpdateUserScreen() {
            DrawScreenHeader(TITLE)

            print("Please Enter Username: ")
            var username = readln()

            while (!User.isUserExists(username)) {
                println("Username ($username) not Found, Please Chose another One: ")
                username = readln()
            }

            val userToUpdate = User.find(username)
            printUser(userToUpdate)

            print("Are you sure you want to update this user y/n? ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                readUserInfo(userToUpdate)
                when (userToUpdate.save()) {
                    SaveResult.Failed -> println(EMPTY_MSG)
                    SaveResult.Succeeded -> {
                        println(SaveResult.Succeeded)
                        printUser(userToUpdate)
                    }
                    SaveResult.FailedAccountNumberExists -> println(ERROR_MSG)
                }
            }

        }
    }
}