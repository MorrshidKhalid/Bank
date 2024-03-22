package org.example.objects

import org.example.Mode
import java.io.File

class Currency(
    mode: Mode,
    val country: String,
    val currencyCode: String,
    val currencyName: String,
    var rate: Float
    ) {


    companion object {

        private const val PATH = "Currencies.txt"
        private const val EMPTY = ""

        private fun getCurrencyEmptyObject(): Currency = Currency(Mode.EmptyMode, EMPTY, EMPTY, EMPTY, 0.0f)

        private fun convertLineToCurrencyObject(data: String, separator: String = "#//#"): Currency {

            val line = data.split(separator)
            return Currency(
                Mode.UpdateMode,
                line[0],
                line[1],
                line[2],
                line[3].toFloat())
        }

        private fun convertCurrencyObjectToLine(currency: Currency, separator: String = "#//#"): String =
            "${currency.country}$separator" +
                    "${currency.currencyCode}$separator" +
                    "${currency.currencyName}$separator" +
                    "${currency.rate}"


        private fun loadCurrencyDataFromFile(): ArrayList<Currency> {
            val file  = File(PATH)

            val arrayOfCurrencies = ArrayList<Currency>()
            if (file.exists())
                file.forEachLine { arrayOfCurrencies.add(convertLineToCurrencyObject(it)) } // Process each line here.
            else println(println("File Record does not exist"))

            return arrayOfCurrencies
        }

        fun findByCode(currencyCode: String): Currency {

            val content = loadCurrencyDataFromFile()
            // Ignore case-sensitive by make country uppercase.
            content.forEach { if (it.currencyCode.uppercase() == currencyCode) return it }

            return getCurrencyEmptyObject()
        }

        fun findByCountry(country: String): Currency {

            val content = loadCurrencyDataFromFile()
            // Ignore case-sensitive by make country uppercase.
            content.forEach { if (it.country.uppercase() == country) return it }

            return getCurrencyEmptyObject()
        }

        fun isCurrencyExistsByCode(currencyCode: String): Boolean = !findByCode(currencyCode).isEmpty()

        fun isCurrencyExistsByCountry(country: String): Boolean = !findByCountry(country).isEmpty()

        fun getCurrenciesList(): ArrayList<Currency>  = loadCurrencyDataFromFile()

    }

    // Specify the object status.
    private var _mode = mode

    fun isEmpty(): Boolean = _mode == Mode.EmptyMode

    private fun getMainCurrencyObject(): Currency = Currency(_mode, country, currencyCode, currencyName, rate)

    private fun save(arrayOfCurrency: ArrayList<Currency>) {

        File(PATH).delete() // Delete old file.
        arrayOfCurrency.forEach {
            File(PATH).appendText("${convertCurrencyObjectToLine(it)}\n") // Overwrite.
        }
    }

    private fun update() {
        val content = loadCurrencyDataFromFile()

        for (elements in 0..< content.size) {
            if (content[elements].currencyCode == this.currencyCode) {
                content[elements] = getMainCurrencyObject()
                break
            }
        }

        save(content)
    }

    fun updateRate(newRate: Float) {
        this.rate = newRate
        update()
    }

    fun convertToOtherCurrency(from: Currency, amount: Float, to: Currency): Float {

        // Calculate to USD.
        if (to.currencyCode == "USD")
            return amount / from.rate


        // Calculate to any currency.
        return (amount / rate) * to.rate
    }

}