import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

const val num_tasks = 1000
const val loops = 500
const val wait_ms = 10L




fun main() = runBlocking {
    launch {
        //Suspending function do not block the thread they are running on but rather
        //suspends it meaning the thread can still be used to do other task
        delay(1000)
        print(" World")
    }
    print("Hello")

    delay(1500)

}


fun old_main() {
    GlobalScope.launch {
        //Suspending function do not block the thread they are running on but rather
        //suspends it meaning the thread can still be used to do other task
        delay(1000)
        print(" World")
    }
    print("Hello")
    //Sleep() blocks the thread the code is running on in this case the main thread
    Thread.sleep(500)
}

