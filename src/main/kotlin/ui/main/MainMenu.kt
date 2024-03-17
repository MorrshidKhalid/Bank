package org.example.ui.main

import lib.InputValidate.Companion.readIntBetween
import org.example.CurrentUser
import org.example.objects.User
import org.example.ui.Screen
import org.example.ui.login.LoginScreen
import org.example.ui.manage.ManageUsers
import org.example.ui.transactions.Transaction


class MainMenu : Screen() {

    companion object {

        private const val MSG_DENIED = "Access Denied Contact your Admin!"

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

        private fun  logoutScreen() {
            CurrentUser.user = User.find("", "")
        }

        fun showMainMenu() {

            DrawScreenHeader("Main Menu Screen")
            println("\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s".
            format("[1] Show client list ",
                "[2] Add New Record ",
                "[3] Delete Record ",
                "[4] Update Record ",
                "[5] Find Record ",
                "[6] Transactions ",
                "[7] Manage Users ",
                "[8] Logout "))

            print("\n======================================================================\n")
            print("Choose what you want to do[1 to 8]? ")

            performMainMenuOption(readIntBetween(1, 8))
        }

        private fun performMainMenuOption(option: Int) {

            when(option) {

                1 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.ShowClientList.num)
                    if (hasPermission) showClientsScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                2 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.AddClient.num)
                    if (hasPermission) showAddNewClientScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                3 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.DeleteClient.num)
                    if (hasPermission) showDeleteClientScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                4 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.UpdateClient.num)
                    if (hasPermission) showUpdateClientScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                5 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.FindClient.num)
                    if (hasPermission) showFindClientScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                6 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.Transactions.num)
                    if (hasPermission) showTransactionScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                7 -> {
                    val hasPermission = User.isUserHasPermission(CurrentUser.user, User.Companion.Permissions.ManageUsers.num)
                    if (hasPermission) showManageUsersScreen()
                    else DrawScreenHeader(MSG_DENIED)

                    goBackToMainMenu()
                }

                8 -> {
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