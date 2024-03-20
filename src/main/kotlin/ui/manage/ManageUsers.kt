package org.example.ui.manage

import lib.InputValidate.Companion.readIntBetween
import org.example.objects.User
import org.example.ui.Screen

class ManageUsers : Screen() {


    companion object {

        private fun showUsersListScreen() {
            UsersListScreen.showUsersListScreen()
        }

        private fun showAddNewUserScreen() {
            AddUserScreen.showAddUserScreen()
        }

        private fun showDeleteUserScreen() {
            DeleteUserScreen.showDeleteUserScreen()
        }

        private fun showUpdateUserScreen() {
            UpdateUserScreen.showUpdateUserScreen()
        }

        private fun showFindUserScreen() {
            FindUserScreen.showFindUserScreen()
        }

        fun showManageUsersManu() {

            if (!userPermissions(User.Companion.Permissions.ManageUsers.num)) // This will exit the function if the user has no access.
                return

            drawScreenHeader("Manage Users Screen")
            println("\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s".
            format("[1] List Users ",
                "[2] Add New User ",
                "[3] Delete User ",
                "[4] Update User ",
                "[5] Find User ",
                "[6] Main Menu "))

            print("\n======================================================================\n")
            print("Choose what you want to do[1 to 6]? ")
            performManageUsersOption(readIntBetween(1, 6))
        }

        private fun goBackToManageUsersMenu() {

            print("\nPress Enter key to back Manage Users Manu\n\n")
            System.`in`.read()
            showManageUsersManu()
        }

        private fun performManageUsersOption(option: Int) {
            when(option) {

                1-> {
                    showUsersListScreen()
                    goBackToManageUsersMenu()
                }

                2-> {
                    showAddNewUserScreen()
                    goBackToManageUsersMenu()
                }

                3-> {
                    showDeleteUserScreen()
                    goBackToManageUsersMenu()
                }

                4-> {
                    showUpdateUserScreen()
                    goBackToManageUsersMenu()
                }

                5-> {
                    showFindUserScreen()
                    goBackToManageUsersMenu()
                }

                6-> {/*Do Nothing*/}

            }
        }

    }
}