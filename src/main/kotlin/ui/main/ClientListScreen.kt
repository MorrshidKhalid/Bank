package org.example.ui.main

import org.example.lib.Util.Companion.line
import org.example.objects.BankClient
import org.example.objects.User
import org.example.ui.Screen

class ClientListScreen : Screen() {

    companion object {

        private val _title = "Client List Screen (${BankClient.getClientsList().size})"

        private fun printLineRecord(client: BankClient) {
            println(
                "%-16s|%-16s|%-30s|%-30s|%-16s|%-16s".format(
                    client.accountNo,
                    client.pinCode,
                    "${client.firstName} ${client.lastName}",
                    client.email,
                    client.phone,
                    client.accountBalance)
            )
        }

        fun showClientsList() {

            if (!userPermissions(User.Companion.Permissions.ShowClientList.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader(_title)

            println("\n%-16s|%-16s|%-30s|%-30s|%-16s|%-16s\n".
            format("Account NO", "PIN", "Account Name", "Email", "Phone", "Balance"))

            val clientsList = BankClient.getClientsList()
            clientsList.forEach {
                printLineRecord(it)
            }

            line()
            println("Total Balances: ${BankClient.getTotalBalances()}")
        }
    }
}