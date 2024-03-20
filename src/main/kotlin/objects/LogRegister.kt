package org.example.objects

class LogRegister(
    val dateAndTime: String,
    val username: String,
    val password: String,
    val permission: String,
    var markForDelete: Boolean = false)