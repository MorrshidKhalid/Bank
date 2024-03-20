package org.example.ui.manage

import org.example.lib.Util.Companion.encryptText
import org.example.SaveResult
import org.example.lib.Util.Companion.line
import org.example.objects.User
import org.example.ui.Screen

class AddUserScreen : Screen() {


    companion object {

        private const val TITLE = "Add User Screen"

        private fun printUser(user: User) {

            println("\nUser Card")
            line()
            println("Username        : ${user.userName}")
            println("Password        : ${user.password}")
            println("Permission num  : ${user.permission}")
            line()
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

            print("Login Register? y/n ")
            ans = readln().lowercase()
            if (ans == "y")
                permission += User.Companion.Permissions.Log.num

            return permission
        }

        private fun readUserInfo(user: User) {

            print("Please Enter Password: ")
            user.password = encryptText(readln())
            user.permission = readPermissionsToSet()
        }


        fun showAddUserScreen() {
            drawScreenHeader(TITLE)

            print("Please Enter Username: ")
            var username = readln()

            while (User.isUserExists(username)) {
                println("Username ($username) Is Already Used, Please Chose another One: ")
                username = readln()
            }

            val newUser = User.getAddNewUser(username)
            readUserInfo(newUser)

            printUser(newUser)

            when (newUser.save()) {

                SaveResult.Failed -> println("User was not saved because it's Empty")
                SaveResult.Succeeded -> println(SaveResult.Succeeded)
                SaveResult.FailedAccountNumberExists -> println("Error User was not saved because username is used!")
            }
        }
    }
}