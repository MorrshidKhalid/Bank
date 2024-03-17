package org.example.objects

import org.example.Mode
import org.example.SaveResult
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
            ManageUsers(64)
        }

        private const val PATH = "Users.txt"
        private const val EMPTY = ""

        private fun loadUsersDataFromFile(): ArrayList<User> {

            val file  = File(PATH)
            val arrayOfUsers = ArrayList<User>()

            if (file.exists())
                file.forEachLine { arrayOfUsers.add(convertLineToUserObject(it)) } // Process each line here.
            else println(println("File $PATH does not exist"))

            return arrayOfUsers
        }

        private fun convertLineToUserObject(line: String, separator: String = "#//#"): User {

            val  lineRecord = line.split(separator)
            return User(
                Mode.UpdateMode,
                lineRecord[0],
                lineRecord[1],
                lineRecord[2].toInt())
        }

        private fun convertUserObjectToLine(user: User, separator: String = "#//#"): String = user.userName + separator + user.password + separator + user.permission.toString()

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
                if (it.userName == userName && it.password == password)
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
