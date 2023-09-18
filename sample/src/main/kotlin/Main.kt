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

