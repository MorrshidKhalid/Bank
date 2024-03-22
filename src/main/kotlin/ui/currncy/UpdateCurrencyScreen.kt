package org.example.ui.currncy

import lib.InputValidate.Companion.readFlt
import org.example.lib.Util.Companion.line
import org.example.objects.Currency
import org.example.ui.Screen

class UpdateCurrencyScreen : Screen() {

    companion object {

        private const val TITLE = "Update Currency Screen"

        private fun printCurrencyCard(currency: Currency) {

            println("\nCurrency Card")
            line()
            println("Country  : ${currency.country}")
            println("Code     : ${currency.currencyCode}")
            println("Name     : ${currency.currencyName}")
            println("Rate     : ${currency.rate}")
        }

        private fun updateCurrencyRate() {
            print("Please Enter Currency Code: ")
            var code = readln().uppercase() // Ignore case-sensitive by make user input uppercase.

            while (!Currency.isCurrencyExistsByCode(code)) {
                print("Code not found, Please chose other one: ")
                code = readln().uppercase()
            }

            val currency = Currency.findByCode(code)
            printCurrencyCard(currency)

            readRate(currency)
        }

        private fun readRate(currency: Currency) {

            println("\nAre you sure you want to update? y/n: ")
            val ans = readln().lowercase()

            if (ans == "y") {
                print("Please Enter New Rate: ")
                val newRate = readFlt()
                currency.updateRate(newRate)
                print("\nRate Updated")
                printCurrencyCard(currency)
            }


        }

        fun showUpdateCurrencyScreen() {
            drawScreenHeader(TITLE)

            updateCurrencyRate()
        }
    }
}