import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Query(
    val type: Int,
    @EncodeDefault val data: String = "",
    @EncodeDefault val sid: String = (System.currentTimeMillis() * 10).toString()
) {
    fun toJsonString() = Json.encodeToString(this)
}
