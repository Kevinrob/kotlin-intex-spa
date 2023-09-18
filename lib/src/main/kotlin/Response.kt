import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.math.BigInteger

@Serializable
data class Response(val sid: String, val data: String, val result: String, val type: Int) {
    @Transient
    val decimalData = BigInteger(data, 16)

    @Transient
    val power = ((decimalData shr 104).toInt() and 0b1) != 0
    @Transient
    val filter = ((decimalData shr 105).toInt() and 0b1) != 0
    @Transient
    val heater = ((decimalData shr 106).toInt() and 0b1) != 0
    @Transient
    val jets = ((decimalData shr 107).toInt() and 0b1) != 0
    @Transient
    val bubbles = ((decimalData shr 108).toInt() and 0b1) != 0
    @Transient
    val sanitizer = ((decimalData shr 109).toInt() and 0b1) != 0

    @Transient
    val currentTemp = ((decimalData shr 88).toInt() and 0xFF).takeUnless { it >= 181 }
    @Transient
    val presetTemp = (decimalData shr 24).toInt() and 0xFF
    @Transient
    val unit = if (presetTemp <= 40) "°C" else "°F"

    @Transient
    val error = ((decimalData shr 88).toInt() and 0xFF).takeIf { it >= 181 }?.let { "E${it - 100}" }

    override fun toString(): String {
        return arrayOf(
            "sid: $sid",
            "data: $data",
            "result: $result",
            "type: $type",
            "power: $power",
            "filter: $filter",
            "heater: $heater",
            "jets: $jets",
            "bubbles: $bubbles",
            "sanitizer: $sanitizer",
            "presetTemp: $presetTemp $unit",
            "currentTemp: $currentTemp $unit",
            "error: $error",
        ).joinToString("\n")
    }
}