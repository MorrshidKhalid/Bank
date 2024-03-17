package org.example.ui.transactions

import org.example.lib.Util.Companion.line
import org.example.objects.BankClient
import org.example.ui.Screen

class TotalBalancesScreen : Screen() {

    companion object {

        private val _all_clients = "Total Balances Screen (${BankClient.getClientsList().size})"

        private fun printLineRecord(client: BankClient) {
            println(
                "%-16s|%-30s|%-16s".format(
                    client.accountNo,
                    "${client.firstName} ${client.lastName}",
                    client.accountBalance)
            )
        }

        fun showTotalBalancesScreen() {
            DrawScreenHeader(_all_clients)

            println("%-16s|%-30s|%-16s".format("AccountNo", "Client Name", "Account Balance"))

            val clientsList = BankClient.getClientsList()
            clientsList.forEach {
                printLineRecord(it)
            }

            line()
            println("Total Balances: ${BankClient.getTotalBalances()}")
        }
    }
}