package lib

import java.util.*

class InputValidate {

    companion object {

        fun isNumberBetween(number: Int, from: Int, to: Int): Boolean {

            return number in from..to
        }

        fun isNumberBetween(number: Double, from: Double, to: Double): Boolean {

            return number in from..to
        }

        fun isNumberBetween(number: Float, from: Float, to: Float): Boolean {

            return number in from..to
        }

        fun readInt(msg: String = "InputMismatchException"): Int {
            val scanner = Scanner(System.`in`)
            var input: Int

            // Make sure the input is only integers.
            do {
                try {
                    input = scanner.nextInt() // Read input.
                } catch (e: InputMismatchException) {
                    println(msg)
                    scanner.next()
                    continue
                }

                break // If the input is an integer out of the loop.
            } while (true)

            return input
        }

        fun readDbl(msg: String = "InputMismatchException"): Double {

            val scanner = Scanner(System.`in`)
            var input: Double

            // Make sure the input is only integers.
            do {
                try {
                    input = scanner.nextDouble() // Read input.
                } catch (e: InputMismatchException) {
                    println(msg)
                    scanner.next()
                    continue
                }

                break // If the input is an integer out of the loop.
            } while (true)

            return input
        }

        fun readFlt(msg: String = "InputMismatchException"): Float {

            val scanner = Scanner(System.`in`)
            var input: Float

            // Make sure the input is only integers.
            do {
                try {
                    input = scanner.nextFloat() // Read input.
                } catch (e: InputMismatchException) {
                    println(msg)
                    scanner.next()
                    continue
                }

                break // If the input is an integer out of the loop.
            } while (true)

            return input
        }

        fun readIntBetween(from: Int, to: Int, msg: String = "Number is not within range"): Int {

            var input = readInt()

            while (!isNumberBetween(input, from, to)) {
                println(msg)
                input = readInt()
            }

            return input
        }

        fun readDblBetween(from: Double, to: Double, msg: String = "Number is not within range"): Double {

            var input = readDbl()

            while (isNumberBetween(input, to, from)) {
                println(msg)
                input = readDbl()
            }
            return input
        }

    }
}