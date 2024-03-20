package org.example.ui.transactions

import lib.InputValidate.Companion.readDbl
import org.example.lib.Util.Companion.line
import org.example.objects.BankClient
import org.example.objects.User
import org.example.ui.Screen

class TransferScreen : Screen() {


    companion object {

        private const val TITLE = "Transfer Screen"

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

        private fun readAccountInfo(fromTo: String): BankClient {

            print("Please Enter Account Number to Transfer $fromTo: ")
            var accountNo = readln()

            while (!BankClient.isClientExists(accountNo)) {
                print("Account Number not fount, Please Enter other one: ")
                accountNo = readln()
            }

            val client = BankClient.find(accountNo)
            printClient(client)

            return client
        }

        private fun readAmount(sourceClient: BankClient): Double {

            print("Please Enter Transfer amount: ")
            var  amount = readDbl()

            while (amount > sourceClient.accountBalance) {
                print("Amount Exceeded Available Balance, Enter Other Amount? ")
                amount = readDbl()
            }

            return amount
        }

        private fun performTransfer(sourceClient: BankClient, destinationClient: BankClient) {

            val amount = readAmount(sourceClient)

            print("Are you sure you want to perform this operation? y/n ")
            val ans = readln().lowercase()
            if (ans == "y") {
                if (sourceClient.transfer(amount, destinationClient)) {
                    println("Transfer Done Successfully")
                }
                else println("Transfer Failed")
            }

            println("\n")
            printClient(sourceClient)
            line()
            printClient(destinationClient)
        }

        fun showTransScreen() {

            drawScreenHeader(TITLE)
            performTransfer(readAccountInfo("From"), readAccountInfo("To"))
        }
    }

}