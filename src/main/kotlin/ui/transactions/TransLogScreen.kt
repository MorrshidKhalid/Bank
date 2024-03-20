package org.example.ui.transactions

import org.example.objects.BankClient
import org.example.objects.TransLog
import org.example.ui.Screen

class TransLogScreen : Screen() {

    companion object {
        private const val TITLE = "Transfer Log Screen"

        private fun printTransferLog(transLog: TransLog) {

            println(
                "%-28s|%-16s|%-16s|%-16s|%-16s|%-16s|%-20s".format(
                    transLog.dateAndTime,
                    transLog.sourceAccount,
                    transLog.destinationAccount,
                    transLog.amount,
                    transLog.sourceAccountAmount,
                    transLog.destinationAccountAmount,
                    transLog.username)
            )
        }

        fun showTransferLogScreen() {
            drawScreenHeader(TITLE)

            println(
                "%-28s|%-16s|%-16s|%-16s|%-16s|%-16s|%-20s\n".format(
                    "DateAndTime",
                    "s.Acct",
                    "d.Acct",
                    "Amount",
                    "s.Balance",
                    "d.Balance",
                    "User")
            )

            val transLogList = BankClient.getTransferList()
            transLogList.forEach { printTransferLog(it) }
        }
    }
}