package org.example.ui.transactions

import lib.InputValidate.Companion.readDbl
import org.example.lib.Util
import org.example.objects.BankClient
import org.example.ui.Screen

class WithdrawScreen : Screen() {


    companion object {

        private const val WITHDRAW_TITLE = "Withdraw Screen"

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

        fun showWithdrawScreen() {
            DrawScreenHeader(WITHDRAW_TITLE)

            var accountNo = readAccountNNo()

            while (!BankClient.isClientExists(accountNo)) {
                print("Account Number ($accountNo) not Found, Please Chose another One: ")
                accountNo = readln()
            }

            val client = BankClient.find(accountNo)
            printClient(client)

            print("Please Enter withdraw amount: ")
            val amount = readDbl()

            print("Are you sure you want to withdraw thia amount? y/n ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                if (client.withdraw(amount)) {
                    println("\nAmount withdraw Successfully.")
                    println("New Balance ${client.accountBalance}")
                } else {
                    println("\nCannot Withdraw, Insufficient Balance!")
                    println("Amount to Withdraw is $amount")
                    println("Your Balance is ${client.accountBalance}")
                }

            } else println("Operation canceled")
        }
    }
}