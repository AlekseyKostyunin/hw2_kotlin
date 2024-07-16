package org.example

sealed interface Command {

    fun isValid(): Boolean

    data class AddPhoneCommand(val name: String, val phone: String) : Command {
        override fun isValid() = phone.matches(Regex("\\+?\\d+"))
    }

    data class AddEmailCommand(val name: String, val email: String) : Command {
        override fun isValid() = email.matches(Regex("\\w+@\\w+\\.\\w+"))
    }

    data object ExitCommand : Command {
        override fun isValid() = true
    }

    data object HelpCommand : Command {
        override fun isValid() = true
    }

    data object ShowCommand : Command {
        override fun isValid() = true
    }
}