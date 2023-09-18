/**
 * Type of the query or response.
 *
 * COMMAND is used for sending commands to the spa. (query)
 * STATUS is used for getting the status of the spa. (response)
 * INFO is used for getting the info of the spa. (query/response)
 */
enum class Type(val type: Int) {
    COMMAND(1),
    STATUS(2),
    INFO(3),
}
