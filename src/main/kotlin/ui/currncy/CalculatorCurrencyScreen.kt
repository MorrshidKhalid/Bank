package org.example.ui.currncy

import lib.InputValidate.Companion.readFlt
import org.example.objects.Currency
import org.example.ui.Screen

class CalculatorCurrencyScreen : Screen() {

    companion object {

        private const val TITLE = "Calculator Currency Screen"
        private const val FROM = "Please Enter Currency Code From: "
        private const val TO = "Please Enter Currency Code To: "

        private fun readCurrency(msg: String): Currency {
            print("\n$msg")

            var code = readln().uppercase() // Ignore case-sensitive by make user input uppercase.

            while (!Currency.isCurrencyExistsByCode(code)) {
                print("Code not found, Please chose other one: ")
                code = readln().uppercase()
            }

            return Currency.findByCode(code)
        }

        private fun printCalc(currencyFrom: Currency, currencyTo: Currency) {

            print("\nPlease Enter amount to Exchange: ")
            val amount = readFlt()

            print("\nConverting from $amount ${currencyFrom.currencyCode} to ${currencyTo.currencyCode}:\t")
            println("${currencyFrom.convertToOtherCurrency(currencyFrom, amount, currencyTo)} ${currencyTo.currencyName}")
        }

        fun showCalculatorCurrencyScreen() {

            drawScreenHeader(TITLE)
            printCalc(readCurrency(FROM), readCurrency(TO)) // Print result of converting currencies.
        }
    }
}