package org.example

import org.example.objects.User

object CurrentUser {

    var user = User.find("", "")
}