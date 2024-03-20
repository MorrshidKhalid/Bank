package org.example.ui.transactions

import lib.InputValidate.Companion.readIntBetween
import org.example.objects.User
import org.example.ui.Screen

class Transaction : Screen() {


    companion object {

        private fun showDepositScreen() {
            DepositScreen.showDepositScreen()
        }

        private fun showWithdrawScreen() {
            WithdrawScreen.showWithdrawScreen()
        }

        private fun showTotalBalancesScreen() {
            TotalBalancesScreen.showTotalBalancesScreen()
        }

        private fun showTransferScreen() {
            TransferScreen.showTransScreen()
        }

        private fun showTransferLogScreen() {
            TransLogScreen.showTransferLogScreen()
        }

        private fun goBackToTransMenu() {

            print("\nPress Enter key to back Main Menu\n\n")
            System.`in`.read()
            showTransactionsMenu()
        }

        private fun performTransMainMenuOption(option: Int) {

            when(option) {

                1 -> {
                    showDepositScreen()
                    goBackToTransMenu()
                }

                2 -> {
                    showWithdrawScreen()
                    goBackToTransMenu()
                }

                3 -> {
                    showTotalBalancesScreen()
                    goBackToTransMenu()
                }

                4 -> {
                    showTransferScreen()
                    goBackToTransMenu()
                }

                5 -> {
                    showTransferLogScreen()
                    goBackToTransMenu()
                }
                6 -> { /*Do Nothing*/ }
            }
        }

        fun showTransactionsMenu() {

            if (!userPermissions(User.Companion.Permissions.Transactions.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader("Transaction Screen")
            println("\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s".
            format("[1] Deposit ",
                "[2] Withdraw ",
                "[3] Total Balance ",
                "[4] Transfer ",
                "[5] Transfer Log",
                "[6] Back to Main Menu "))

            print("\n======================================================================\n")
            print("Choose what you want to do[1 to 6]? ")

            performTransMainMenuOption(readIntBetween(1, 6))
        }
    }
}