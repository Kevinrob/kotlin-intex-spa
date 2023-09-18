enum class TemperatureUnit(private val symbol: String) {
    CELSIUS("°C"), FAHRENHEIT("°F");

    override fun toString() = symbol
}
