import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.math.floor

class Spa(ip: String) {
    private val client = Socket(ip, 8990)
    private val output = PrintWriter(client.getOutputStream(), true)
    private val input = BufferedReader(InputStreamReader(client.inputStream))

    fun sendCommand(command: Command): Response {
        val query = Query(
            data = command.request + checksum(command.request),
            type = Type.COMMAND.type
        )
        println("Sending: $query")

        output.println(Json.encodeToJsonElement(query))

        val rawResponse = input.readLine()

        return Json.decodeFromString<Response>(rawResponse)
    }

    private fun checksum(data: String) = String.format("%02X", checksumAsInt(data))

    private fun checksumAsInt(data: String): Int {
        var calculatedChecksum = 0xFF
        for (index in data.indices step 2) {
            calculatedChecksum -= data.substring(index, index + 2).toInt(16)
        }
        calculatedChecksum = (calculatedChecksum - floor(calculatedChecksum / 255f) * 255).toInt()
        if (calculatedChecksum == 0) {
            calculatedChecksum = 0xFF
        }
        return calculatedChecksum
    }

    fun close() = client.close()
}