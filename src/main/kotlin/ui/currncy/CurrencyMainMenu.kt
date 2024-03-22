package org.example.ui.currncy

import lib.InputValidate
import org.example.ui.Screen

class CurrencyMainMenu : Screen() {

    companion object {

        private const val TITLE = "Currencies Main Menu"

        private fun showListCurrencies() {
            CurrencyListScreen.showCurrencyListScreen()
        }

        private fun showFindCurrency() {
            FindCurrencyScreen.showFindCurrencyScreen()
        }

        private fun showUpdateRate() {
            UpdateCurrencyScreen.showUpdateCurrencyScreen()
        }

        private fun showCurrencyCalculator() {
            CalculatorCurrencyScreen.showCalculatorCurrencyScreen()
        }

        private fun goBackToCurrencyMenu() {

            print("\nPress Enter key to back Currency Menu\n\n")
            System.`in`.read()
            showCurrenciesMenu()
        }

        private fun performCurrencyMenuOption(option: Int) {

            when(option) {

                1 -> {
                    showListCurrencies()
                    goBackToCurrencyMenu()
                }

                2 -> {
                    showFindCurrency()
                    goBackToCurrencyMenu()
                }

                3 -> {
                    showUpdateRate()
                    goBackToCurrencyMenu()
                }

                4 -> {
                    showCurrencyCalculator()
                    goBackToCurrencyMenu()
                }

                5 -> {
                    /*Do Nothing*/
                }
            }
        }

        fun showCurrenciesMenu() {

            drawScreenHeader(TITLE)
            println("\n%-25s\n%-25s\n%-25s\n%-25s\n%-25s".
            format("[1] List Currencies. ",
                "[2] Find Currency. ",
                "[3] Update Rate. ",
                "[4] Currency Calculator. ",
                "[5] Back to Main Menu "))

            print("\n======================================================================\n")
            print("Choose what you want to do[1 to 5]? ")

            performCurrencyMenuOption(InputValidate.readIntBetween(1, 5))
        }
    }
}