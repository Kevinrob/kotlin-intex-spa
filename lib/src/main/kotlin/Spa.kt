import kotlinx.serialization.json.Json
import okio.buffer
import okio.sink
import okio.source
import java.net.Socket
import java.net.SocketTimeoutException
import kotlin.math.floor

class Spa(private val ip: String) {
    private lateinit var socket: Socket

    init {
        connectSocket()
    }

    fun sendInfo(): InfoResponse? {
        val query = Query(type = Type.INFO.type, data = "")
        val rawResponse = callSpa(query.toJsonString())

        return rawResponse?.let { Json.decodeFromString<InfoResponse>(it) }
    }

    fun sendCommand(command: Command): StatusResponse? {
        val query = Query(
            type = Type.COMMAND.type,
            data = command.request + checksum(command.request),
        )
        println(query)
        val rawResponse = callSpa(query.toJsonString())

        return rawResponse?.let { Json.decodeFromString<StatusResponse>(it) }
    }

    private fun callSpa(command: String): String? {
        synchronized(this) {
            println("Sending: $command")

            var attempt = 0
            var response: String? = null
            while (response == null && attempt < 3) {
                try {
                    socket.sink().buffer().write(command.toByteArray()).flush()
                    response = socket.source().buffer().readUtf8Line()
                } catch (e: SocketTimeoutException) {
                    Thread.sleep(2000)

                    connectSocket()
                    attempt++
                }
            }

            return response
        }
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

    private fun connectSocket() {
        socket = Socket(ip, 8990).apply {
            soTimeout = 2000
        }
    }
}