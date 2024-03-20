package org.example.ui

import org.example.CurrentUser
import org.example.lib.Date.Companion.getCurrentDateAndTime
import org.example.lib.Util.Companion.title
import org.example.objects.User

open class Screen {

    protected companion object {

        private const val MSG_DENIED = "Access Denied Contact your Admin!"
        fun drawScreenHeader(mainTitle: String) {
            currentUserAndDateTime()
            title(mainTitle)
        }

        // Check If User has Permission on a System Page.
        fun userPermissions(option: Int): Boolean {

            val userPermission = User.isUserHasPermission(CurrentUser.user, option)
            if (!userPermission) {
                drawScreenHeader(MSG_DENIED)
                return false
            }

            return true
        }

        // Print the current system user, date, and time.
        private fun currentUserAndDateTime() {
            println("\n\nUser: %-45s Date: %s".format(CurrentUser.user.userName, getCurrentDateAndTime()))
        }
    }
}