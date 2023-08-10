import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import run_blocking.doWork
import kotlin.test.assertEquals

class ApplicationTests {

    @Test
    fun first_test() = runBlocking{
        doWork()
        assertEquals(4,2+2)
    }
}