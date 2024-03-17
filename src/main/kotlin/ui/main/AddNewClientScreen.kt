package org.example.ui.main

import org.example.SaveResult
import org.example.lib.Util.Companion.line
import org.example.objects.BankClient
import org.example.ui.Screen

class AddNewClientScreen : Screen() {

    companion object {
        private const val TITLE = "Add New Client Screen"

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

        fun showAddNewClientScreen() {
            DrawScreenHeader(TITLE)

            print("Please Enter Account Number: ")
            var accountNo = readln()

            while (BankClient.isClientExists(accountNo)) {
                println("Account Number ($accountNo) Is Already Used, Please Chose another One: ")
                accountNo = readln()
            }

            val newClient = BankClient.getAddNewClient(accountNo)
            readClientInfo(newClient)

            when(newClient.save()) {

                SaveResult.Failed -> println("Account was not saved because it's Empty")

                SaveResult.Succeeded -> {
                    printClient(newClient)
                    print(SaveResult.Succeeded)
                }

                SaveResult.FailedAccountNumberExists -> println("Error account was not saved because account number is used!")
            }

        }
    }

}