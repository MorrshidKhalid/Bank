package org.example.ui.main

import org.example.SaveResult
import org.example.lib.Util
import org.example.objects.BankClient
import org.example.objects.User
import org.example.ui.Screen

class UpdateClientScreen : Screen() {

    companion object {

        private const val TITLE = "Update Client Screen"
        private const val ERROR_MSG = "Error account was not saved because account number is used!"
        private const val EMPTY_MSG = "Account was not saved because it's Empty"

        private fun printClient(client: BankClient) {

            println("\nClient Card")
            Util.line()
            println("Full name     : ${client.firstName} ${client.lastName}")
            println("Email         : ${client.email}")
            println("Phone         : ${client.phone}")
            println("Acc. Number   : ${client.accountNo}")
            println("Password      : ${client.pinCode}")
            println("Balance       : ${client.accountBalance}")
            Util.line()
        }

        private fun readClientInfo(client: BankClient) {

            print("Please Enter Firstname: ")
            client.firstName = readln()
            print("Please Enter Lastname: ")
            client.lastName = readln()
            print("Please Enter Email: ")
            client.email = readln()
            print("Please Enter Phone Number: ")
            client.phone = readln()
            print("Please Enter Password: ")
            client.pinCode = readln()
            print("Please Enter Balance: ")
            client.accountBalance = readln().toDouble()
        }

        fun showUpdateClientScreen() {

            if (!userPermissions(User.Companion.Permissions.UpdateClient.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader(TITLE)

            print("Please Enter Account Number: ")
            var accountNo = readln()

            while (!BankClient.isClientExists(accountNo)) {
                print("Account Number ($accountNo) not Found, Please Chose another One: ")
                accountNo = readln()
            }

            val clientToUpdate = BankClient.find(accountNo)
            printClient(clientToUpdate)

            print("Are you sure you want to update this client y/n? ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                readClientInfo(clientToUpdate)
                when (clientToUpdate.save()) {
                    SaveResult.Failed -> println(EMPTY_MSG)
                    SaveResult.Succeeded -> println(SaveResult.Succeeded)
                    SaveResult.FailedAccountNumberExists -> println(ERROR_MSG)
                }
            }



        }
    }
}