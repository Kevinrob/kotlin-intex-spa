import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class QueryTest {
    @Test
    fun toJsonString() {
        val queryInfo = Query(type = Type.INFO.type, data = "")
        assertEquals("""{"type":3,"data":"","sid":"${queryInfo.sid}"}""", queryInfo.toJsonString())

        val queryCommand = Query(type = Type.COMMAND.type, data = "test")
        assertEquals("""{"type":1,"data":"test","sid":"${queryCommand.sid}"}""", queryCommand.toJsonString())
    }
}