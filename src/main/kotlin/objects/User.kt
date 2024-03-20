package org.example.objects

import org.example.lib.Util.Companion.decryptText
import org.example.CurrentUser
import org.example.Mode
import org.example.SaveResult
import org.example.lib.Date.Companion.getCurrentDateAndTime
import java.io.File

class User(
    mode: Mode,
    var userName: String,
    var password: String,
    var permission: Int,
    var markForDelete: Boolean = false) {

    companion object {

        enum class Permissions(val num: Int) {
            All(-1),
            ShowClientList(1),
            AddClient(2),
            DeleteClient(4),
            UpdateClient(8),
            FindClient(16),
            Transactions(32),
            ManageUsers(64),
            Log(128)
        }

        private const val PATH = "Users.txt"
        private const val LOG_PATH = "Log.txt"
        private const val EMPTY = ""

        private fun loadUsersDataFromFile(): ArrayList<User> {

            val file  = File(PATH)
            val arrayOfUsers = ArrayList<User>()

            if (file.exists())
                file.forEachLine { arrayOfUsers.add(convertLineToUserObject(it)) } // Process each line here.
            else println(println("File $PATH does not exist"))

            return arrayOfUsers
        }

        private fun loadLogDataFromFile(): ArrayList<LogRegister> {

            val file  = File(LOG_PATH)
            val arrayOfLogs = ArrayList<LogRegister>()

            if (file.exists())
                file.forEachLine { arrayOfLogs.add(convertLineToLogObject(it)) } // Process each line here.
            else println(println("File $LOG_PATH does not exist"))

            return arrayOfLogs
        }

        private fun convertLineToUserObject(line: String, separator: String = "#//#"): User {

            val  lineRecord = line.split(separator)
            return User(
                Mode.UpdateMode,
                lineRecord[0],
                lineRecord[1],
                lineRecord[2].toInt())
        }

        private fun convertLineToLogObject(line: String, separator: String = "#//#"): LogRegister {

            val  logLine = line.split(separator)
            return LogRegister(
                logLine[0],
                logLine[1],
                logLine[2],
                logLine[3])
        }

        fun convertUserObjectToLine(user: User, separator: String = "#//#"): String = user.userName + separator + user.password + separator + user.permission.toString()

        private fun prepareRegisterLine(separator: String = "#//#"): String =
                    "${getCurrentDateAndTime()}$separator" +
                    "${CurrentUser.user.userName}$separator" +
                    "${CurrentUser.user.password}$separator" +
                    "${CurrentUser.user.permission}"

        private fun getEmptyUserObject(): User = User(Mode.EmptyMode, EMPTY, EMPTY, 0)

        fun getAddNewUser(userName: String): User = User(Mode.AddNewMode, userName, EMPTY, 0)

        fun find(userName: String): User {

            // Content in the clients file.
            val content = loadUsersDataFromFile()
            content.forEach {
                if (it.userName == userName)
                    return it
            }

            return getEmptyUserObject()
        }

        fun find(userName: String, password: String): User {

            // Content in the clients file.
            val content = loadUsersDataFromFile()
            content.forEach {
                if (it.userName == userName && decryptText(it.password) == password)
                    return it
            }

            return getEmptyUserObject()
        }

        private fun saveUserDataToFile(arrayOfUsers: ArrayList<User>) {

            File(PATH).delete() // Delete old file.
            arrayOfUsers.forEach {
                if (!it.markForDelete)
                    File(PATH).appendText("${convertUserObjectToLine(it)}\n") // Overwrite.
            }
        }

        fun isUserExists(userName: String): Boolean = !find(userName).isEmpty()

        fun isUserHasPermission(user: User, option: Int): Boolean {

            if (user.permission == -1)
                return true

            return user.permission and option == option
        }

        fun getUserList(): ArrayList<User> = loadUsersDataFromFile()

        fun getLogList(): ArrayList<LogRegister> = loadLogDataFromFile()

        fun addNewRegister() {
            File(LOG_PATH).appendText("${prepareRegisterLine()}\n")
        }

    }

    // Specify the object status.
    private var _mode = mode

    fun isEmpty(): Boolean = _mode == Mode.EmptyMode

    private fun getMainClientObject(): User = User(_mode, userName, password, permission)

    private fun setEmptyUserObject() {

        this._mode = Mode.EmptyMode
        this.userName =EMPTY
        this.password = EMPTY
        this.permission = 0

    }

    private fun update() {

        val content = loadUsersDataFromFile()
        for (elements in 0..< content.size) {
            if (content[elements].userName == userName) {
                content[elements] = getMainClientObject()
                break
            }
        }

        saveUserDataToFile(content) // Update data in file.
    }

    private fun addNew() {
        File(PATH).appendText("${convertUserObjectToLine(getMainClientObject())}\n")
    }

    fun delete(): Boolean {

        if (this.userName == "Admin")
            return false

        val content = loadUsersDataFromFile()

        for (element in 0..< content.size) {
            if (content[element].userName == userName) {
                content[element].markForDelete = true
                break
            }
        }

        saveUserDataToFile(content)
        setEmptyUserObject()

        return true
    }

    fun save(): SaveResult = when(_mode) {
        Mode.EmptyMode -> SaveResult.Failed
        Mode.UpdateMode -> {
            update()
            SaveResult.Succeeded
        }
        Mode.AddNewMode -> {
            if (isUserExists(userName)) SaveResult.FailedAccountNumberExists
            else {
                addNew()
                _mode = Mode.UpdateMode
                SaveResult.Succeeded
            }
        }
    }
}