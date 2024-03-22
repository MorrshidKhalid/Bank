package org.example.ui.main

import lib.InputValidate.Companion.readIntBetween
import org.example.CurrentUser
import org.example.objects.User
import org.example.ui.Screen
import org.example.ui.currncy.CurrencyMainMenu
import org.example.ui.manage.ManageUsers
import org.example.ui.transactions.Transaction


class MainMenu : Screen() {

    companion object {

        private fun  showClientsScreen() {
            ClientListScreen.showClientsList()
        }

        private fun  showAddNewClientScreen() {
            AddNewClientScreen.showAddNewClientScreen()
        }

        private fun  showDeleteClientScreen() {
            DeleteClientScreen.showDeleteClientScreen()
        }

        private fun  showUpdateClientScreen() {
            UpdateClientScreen.showUpdateClientScreen()
        }

        private fun  showFindClientScreen() {
            FindClientScreen.showFindClientScreen()
        }

        private fun  showTransactionScreen() {
            Transaction.showTransactionsMenu()
        }

        private fun  showManageUsersScreen() {
            ManageUsers.showManageUsersManu()
        }

        private fun showCurrenciesMenu() {
            CurrencyMainMenu.showCurrenciesMenu()
        }
        private fun  showLogScreen() {
            LogScreen.showLogRegisterScreen()
        }

        private fun  logoutScreen() {
            CurrentUser.user = User.find("", "")
        }

        fun showMainMenu() {

            drawScreenHeader("Main Menu Screen")
            println("\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s".
            format("[1] Show client list. ",
                "[2] Add New Record. ",
                "[3] Delete Record. ",
                "[4] Update Record. ",
                "[5] Find Record. ",
                "[6] Transactions. ",
                "[7] Manage Users. ",
                "[8] Login Register. ",
                "[9] Currency Exchange. ",
                "[10] Logout. "))

            print("\n======================================================================\n")
            print("Choose what you want to do[1 to 10]? ")

            performMainMenuOption(readIntBetween(1, 10))
        }

        private fun performMainMenuOption(option: Int) {

            when(option) {

                1 -> {
                    showClientsScreen()
                    goBackToMainMenu()
                }

                2 -> {
                    showAddNewClientScreen()
                    goBackToMainMenu()
                }

                3 -> {
                    showDeleteClientScreen()
                    goBackToMainMenu()
                }

                4 -> {
                    showUpdateClientScreen()
                    goBackToMainMenu()
                }

                5 -> {
                    showFindClientScreen()
                    goBackToMainMenu()
                }

                6 -> {
                    showTransactionScreen()
                    goBackToMainMenu()
                }

                7 -> {
                    showManageUsersScreen()
                    goBackToMainMenu()
                }

                8 -> {
                    showLogScreen()
                    goBackToMainMenu()
                }

                9 -> {
                    showCurrenciesMenu()
                    goBackToMainMenu()
                }

                10 -> {
                    logoutScreen()
                }
            }
        }

        private fun goBackToMainMenu() {

            print("\nPress Enter key to back Main Menu\n\n")
            System.`in`.read()
            showMainMenu()
        }
    }
}