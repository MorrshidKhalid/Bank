package org.example.ui.currncy

import org.example.objects.Currency
import org.example.ui.Screen

class CurrencyListScreen : Screen() {


    companion object {

        private const val TITLE = "List Currency Screen"

        private fun printCurrencyLine(currency: Currency) {
            println("%-30s|%-12s|%-30s|%-12s".
            format(currency.country, currency.currencyCode, currency.currencyName, currency.rate)
            )
        }

        fun showCurrencyListScreen() {
            drawScreenHeader(TITLE)

            println("%-30s|%-12s|%-30s|%-12s".format("Country", "Code", "Name", "Rate\n"))

            val content = Currency.getCurrenciesList()
            content.forEach { printCurrencyLine(it) }

        }
    }
}