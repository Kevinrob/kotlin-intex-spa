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