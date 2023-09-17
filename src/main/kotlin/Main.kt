fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val spa = Spa("192.168.1.65")

    println(spa.sendCommand(Command.STATUS))

    //println(spa.sendCommand(Command.BUBBLES))
    //Thread.sleep(2000)
    //println(spa.sendCommand(Command.BUBBLES))

    spa.close()
}

enum class Type(val type: Int) {
    COMMAND(1),
    STATUS(2),
    INFO(3),
}

enum class Command(val request: String) {
    STATUS("8888060FEE0F01"),
    POWER("8888060F014000"),
    FILTER("8888060F010004"),
    HEATER("8888060F010010"),
    JETS("8888060F011000"),
    BUBBLES("8888060F010400"),
    SANITIZER("8888060F010001"),
    PRESET_TEMP("8888050F0C")
}
