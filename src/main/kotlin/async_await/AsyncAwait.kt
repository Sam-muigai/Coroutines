package async_await

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    val time = measureTimeMillis {
        val work1 = async { doWork1() }
        val work2 = async { doWork2() }
        println(work1.await() + work2.await())
    }

    val time2 = measureTimeMillis {
        val work1 =  doWork1()
        val work2 =  doWork2()
        println(work1 + work2)
    }

    println("Time using async $time")


    println("Time without async $time2")

}


suspend fun doWork1():Int{
    delay(1000)
    return Random(1000).nextInt()
}
suspend fun doWork2():Int{
    delay(2000)
    return Random(1000).nextInt()
}