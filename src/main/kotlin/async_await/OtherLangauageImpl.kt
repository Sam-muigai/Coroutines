package async_await

import kotlinx.coroutines.*

fun main() = runBlocking {
    val number = doWorkAsync().await()
    val number2 = async{  doWorkAsync2() }
    println(number2)
}

fun doWorkAsync(): Deferred<Int> = GlobalScope.async{
    delay(500)
    return@async 42
}


suspend fun doWorkAsync2():Int{
    delay(500)
    return 42
}