package org.example.ui.main

import org.example.lib.Util.Companion.line
import org.example.objects.BankClient
import org.example.objects.User
import org.example.ui.Screen

class FindClientScreen : Screen() {

    companion object {

        private const val TITLE = "Find Client Screen"

        private fun printClient(client: BankClient) {

            println("\nClient Card")
            line()
            println("Full name     : ${client.firstName} ${client.lastName}")
            println("Email         : ${client.email}")
            println("Phone         : ${client.phone}")
            println("Acc. Number   : ${client.accountNo}")
            println("Password      : ${client.pinCode}")
            println("Balance       : ${client.accountBalance}")
            line()
        }

        fun showFindClientScreen() {

            if (!userPermissions(User.Companion.Permissions.FindClient.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader(TITLE)

            print("Please Enter Account Number: ")
            var accountNo = readln()

            while (!BankClient.isClientExists(accountNo)) {
                print("Account Number ($accountNo) not Found, Please Chose another One: ")
                accountNo = readln()
            }

            printClient(BankClient.find(accountNo))
        }
    }
}