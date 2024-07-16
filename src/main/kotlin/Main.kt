package org.example

fun main() {
    println(
        "Введите одну из команд:\n" +
                "1. exit\n" +
                "2. help\n" +
                "3. add <Имя> phone <Номер телефона>\n" +
                "4. add <Имя> email <Адрес электронной почты>\n" +
                "5. show"
    )
    var person: Person? = null
    while (true) {
        val command = readCommand(readLine()!!)
        println(command)
        if (command.isValid()) {
            when (command) {
                is Command.AddPhoneCommand -> {
                    person = Person(command.name, phone = command.phone)
                    println("Добавлено: ${person!!.name}, телефон: ${person!!.phone}")
                }

                is Command.AddEmailCommand -> {
                    person = Person(command.name, email = command.email)
                    println("Добавлено: ${person!!.name}, email: ${person!!.email}")
                }

                is Command.ShowCommand -> {
                    if (person == null) {
                        println("Not initialized")
                    } else {
                        println("Последняя запись: $person")
                    }
                }

                is Command.HelpCommand -> {
                    println(
                        "Список доступных команд:\n" +
                                "1. exit\n" +
                                "2. help\n" +
                                "3. add <Имя> phone <Номер телефона>\n" +
                                "4. add <Имя> email <Адрес электронной почты>\n" +
                                "5. show"
                    )
                }

                is Command.ExitCommand -> return
                else -> println("Неизвестная команда")
            }
        } else {
            println("Неверный формат команды, попробуйте еще раз")
        }
    }
}

fun readCommand(input: String): Command {
    val parts = input.split(" ")
    return when (parts[0]) {
        "add" -> {
            if (parts.size == 4) {
                when (parts[2]) {
                    "phone" -> Command.AddPhoneCommand(parts[1], parts[3])
                    "email" -> Command.AddEmailCommand(parts[1], parts[3])
                    else -> Command.HelpCommand
                }
            } else {
                return Command.HelpCommand
            }
        }
        "exit" -> Command.ExitCommand
        "help" -> Command.HelpCommand
        "show" -> Command.ShowCommand
        else -> Command.HelpCommand
    }
}