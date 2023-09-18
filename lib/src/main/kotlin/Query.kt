import kotlinx.serialization.Serializable

@Serializable
data class Query(val data: String, val type: Int, val sid: String = (System.currentTimeMillis() * 10).toString())