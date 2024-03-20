package org.example.ui.transactions

import lib.InputValidate.Companion.readDbl
import org.example.lib.Util
import org.example.objects.BankClient
import org.example.ui.Screen

class DepositScreen : Screen() {

    companion object {

        private const val DEPOSIT_TITLE = "Deposit Screen"

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

        private fun readAccountNNo(): String {
            print("Please Account No: ")
            return readln()
        }

        fun showDepositScreen() {
            drawScreenHeader(DEPOSIT_TITLE)

            var accountNo = readAccountNNo()

            while (!BankClient.isClientExists(accountNo)) {
                print("Account Number ($accountNo) not Found, Please Chose another One: ")
                accountNo = readln()
            }

            val client = BankClient.find(accountNo)
            printClient(client)

            print("Please Enter Deposit amount: ")
            val amount = readDbl()

            print("Are you sure you want to deposit thia amount? y/n ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                client.deposit(amount)
                println("\nAmount deposit Successfully.")
                println("New Balance ${client.accountBalance}")
            } else println("Operation canceled")
        }

    }
}