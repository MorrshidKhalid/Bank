package org.example.ui.currncy

import org.example.lib.Util.Companion.line
import lib.InputValidate.Companion.readIntBetween
import org.example.objects.Currency
import org.example.ui.Screen

class FindCurrencyScreen : Screen() {

    companion object {

        private const val TITLE = "Find Currency Screen"

        private fun printCurrencyCard(currency: Currency) {

            println("\nCurrency Card")
            line()
            println("Country  : ${currency.country}")
            println("Code     : ${currency.currencyCode}")
            println("Name     : ${currency.currencyName}")
            println("Rate     : ${currency.rate}")
        }

        private fun readCurrencyCode() {
            print("Please Enter Currency Code: ")
            var code = readln().uppercase() // Ignore case-sensitive by make user input uppercase.

            while (!Currency.isCurrencyExistsByCode(code)) {
                print("Code not found, Please chose other one: ")
                code = readln().uppercase()
            }

            val currency = Currency.findByCode(code)
            printCurrencyCard(currency)
        }

        private fun readCurrencyCountry() {
            print("Please Enter Country: ")
            var country = readln().uppercase() // Ignore case-sensitive by make user input uppercase.

            while (!Currency.isCurrencyExistsByCountry(country)) {
                print("Country not found, Please chose other one: ")
                country = readln().uppercase()
            }

            val currency = Currency.findByCountry(country)
            printCurrencyCard(currency)
        }

        private fun readOption() {
            print("Find By Code[1], Find By Country[2]: ")
            val option = readIntBetween(1, 2)

            when (option) {
                1 -> {
                    readCurrencyCode()
                }

                2 -> {
                    readCurrencyCountry()
                }
            }
        }

        fun showFindCurrencyScreen() {
            drawScreenHeader(TITLE)

            readOption()
        }

    }
}