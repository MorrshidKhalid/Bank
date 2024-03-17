package org.example.lib

class Util {


    companion object {

        fun isPunctuation(char: Char): Boolean {
            return char in '!'..'/'
                    || char in ':'..'@'
                    || char in '['..'`'
                    || char in '{'..'~'
        }

        fun rmPunctuation(str: String): String {

            var newString = "" // The new string without punctuation.

            for (elements in str)
                if (!isPunctuation(elements))
                    newString += elements

            return newString
        }

        fun title(title: String) {

            println("\n------------------------------------------------------------------------")
            println("%40s".format(title))
            println("------------------------------------------------------------------------")
        }

        fun line() {
            println("_________________________________________")
        }

    }
}