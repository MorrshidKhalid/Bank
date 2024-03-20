package org.example.ui.main

import org.example.objects.BankClient
import org.example.objects.LogRegister
import org.example.objects.User
import org.example.ui.Screen

class LogScreen : Screen() {

    companion object {

        private const val TITLE = "Login Users Screen"

        private fun printLogLine(loginRegister: LogRegister) {
            println(
                "%-28s|%-25s|%-16s|%-16s".format(
                    loginRegister.dateAndTime,
                    loginRegister.username,
                    loginRegister.password,
                    loginRegister.permission
                    )
            )
        }

        fun showLogRegisterScreen() {

            if (!userPermissions(User.Companion.Permissions.Log.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader(TITLE)

            println("%-28s|%-25s|%-16s|%-16s\n".format("Date and Time", "Username", "Password", "Permission"))

            val loginRegisterList = User.getLogList()
            loginRegisterList.forEach { printLogLine(it) }
        }
    }

}