package org.example.objects

import org.example.Mode
import org.example.SaveResult
import java.io.File

class BankClient(
    mode: Mode,
    var accountNo: String,
    var pinCode: String,
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    var accountBalance: Double,
    var markForDelete: Boolean = false) : Person(firstName, lastName, email, phone) {


    companion object {

        private const val PATH = "Clients.txt"
        private const val EMPTY = ""

        private fun loadClientsDataFromFile(): ArrayList<BankClient> {

            val file  = File(PATH)
            val arrayOfClients = ArrayList<BankClient>()

            if (file.exists())
                file.forEachLine { arrayOfClients.add(convertLineToClientObject(it)) } // Process each line here.
            else println(println("File Record does not exist"))

            return arrayOfClients
        }

        private fun convertLineToClientObject(line: String, separator: String = "#//#"): BankClient {

            val  lineRecord = line.split(separator)
            return BankClient(
                Mode.UpdateMode,
                lineRecord[0],
                lineRecord[1],
                lineRecord[2],
                lineRecord[3],
                lineRecord[4],
                lineRecord[5],
                lineRecord[6].toDouble())
        }

        private fun convertClientObjectToLine(client: BankClient, separator: String = "#//#"): String {

            return client.accountNo + separator +
                   client.pinCode + separator +
                   client.firstName + separator +
                   client.lastName + separator +
                   client.email + separator +
                   client.phone + separator +
                   client.accountBalance.toString()
        }

        private fun getEmptyClientObject(): BankClient = BankClient(Mode.EmptyMode, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, 0.0)

        fun getAddNewClient(accountNo: String): BankClient = BankClient(Mode.AddNewMode, accountNo, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, 0.0)

        fun find(accountNo: String): BankClient {

            // Content in the clients file.
            val content = loadClientsDataFromFile()
            content.forEach {
                if (it.accountNo == accountNo)
                    return it
            }

            return getEmptyClientObject()
        }

        fun find(accountNo: String, password: String): BankClient {

            // Content in the clients file.
            val content = loadClientsDataFromFile()
            content.forEach {
                if (it.accountNo == accountNo && it.pinCode == password)
                    return it
            }

            return getEmptyClientObject()
        }

        private fun saveClientDataToFile(arrayOfClient: ArrayList<BankClient>) {

            File(PATH).delete() // Delete old file.
            arrayOfClient.forEach {
                if (!it.markForDelete)
                    File(PATH).appendText("${convertClientObjectToLine(it)}\n") // Overwrite.
            }
        }

        fun isClientExists(accountNo: String): Boolean = !find(accountNo).isEmpty()

        fun getClientsList(): ArrayList<BankClient> = loadClientsDataFromFile()

        fun getTotalBalances(): Double {

            val content = loadClientsDataFromFile()
            var total = 0.0
            content.forEach { total += it.accountBalance }
            return total
        }

    }

    // Specify the object status.
    private var _mode = mode

    fun isEmpty(): Boolean = _mode == Mode.EmptyMode

    private fun getMainClientObject(): BankClient = BankClient(_mode, accountNo, pinCode, firstName, lastName, email, phone, accountBalance)

    private fun update() {

        val content = loadClientsDataFromFile()
        for (elements in 0..< content.size) {
            if (content[elements].accountNo == accountNo) {
                content[elements] = getMainClientObject()
                break
            }
        }

        saveClientDataToFile(content) // Update data in file.
    }

    private fun addNew() {
        File(PATH).appendText("${convertClientObjectToLine(getMainClientObject())}\n")
    }

    private fun setEmptyClientObject() {

        this._mode = Mode.EmptyMode
        this.firstName = EMPTY
        this.lastName = EMPTY
        this.email = EMPTY
        this.phone = EMPTY
        this.accountNo = EMPTY
        this.pinCode = EMPTY
        this.accountBalance = 0.0

    }

    fun delete(): Boolean {

        val content = loadClientsDataFromFile()

        for (element in 0..< content.size) {
            if (content[element].accountNo == accountNo) {
                content[element].markForDelete = true
                break
            }
        }

        saveClientDataToFile(content)
        setEmptyClientObject()

        return true
    }

    fun save(): SaveResult = when(_mode) {
        Mode.EmptyMode -> SaveResult.Failed
        Mode.UpdateMode -> {
            update()
            SaveResult.Succeeded
        }
        Mode.AddNewMode -> {
            if (isClientExists(accountNo)) SaveResult.FailedAccountNumberExists
                else {
                    addNew()
                    _mode = Mode.UpdateMode
                    SaveResult.Succeeded
            }
        }
    }

    fun deposit(amount: Double) {
        this.accountBalance += amount
        save()
    }

    fun withdraw(amount: Double): Boolean {
        if (amount > this.accountBalance)
            return false

        this.accountBalance -= amount
        save()
        return true
    }

}