package run_blocking

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//RunBlocking is a coroutine builder used either in testing or in main function
fun main() = runBlocking {
    launch {
        //Suspending function do not block the thread they are running on but rather
        //suspends it meaning the thread can still be used to do other task
        delay(1000)
        print(" World")
    }

    doWork()
    delay(1500)

}

suspend fun doWork() {
    delay(500)
   print("Hello")
}
