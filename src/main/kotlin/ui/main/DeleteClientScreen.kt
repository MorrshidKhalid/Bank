package org.example.ui.main

import org.example.lib.Util
import org.example.objects.BankClient
import org.example.ui.Screen

class DeleteClientScreen : Screen() {

    companion object {

        private const val TITLE = "Delete Client Screen"

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

        fun showDeleteClientScreen() {
            DrawScreenHeader(TITLE)

            print("Please Enter Account Number: ")
            var accountNo = readln()

            while (!BankClient.isClientExists(accountNo)) {
                println("Account Number ($accountNo) not Found, Please Chose another One: ")
                accountNo = readln()
            }

            val clientToDelete = BankClient.find(accountNo)
            printClient(clientToDelete)

            print("Are you sure you want to delete this client y/n? ")
            val option = readln()
            option.lowercase()

            if (option == "y") {
                if (clientToDelete.delete()) {
                    println("Client deleted Successfully :)")
                    printClient(clientToDelete)
                } else println("Error Client was not deleted :(")
            }
        }
    }
}