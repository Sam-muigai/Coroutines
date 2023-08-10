package join_coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    launch {
        runWithLocalScope()
        println("Run With local scope returned")
        delay(3000)
    }
}







/*
* Structured concurrency is observed.coroutineScope builder
*  allows coroutines to run in parallel and waits for
*  all of them to finish so that they can return
* */
suspend fun runWithLocalScope(){
    coroutineScope {
        launch {
            println("Launch 1")
            delay(1000)
        }
        launch {
            delay(1000)
            println("Launch 2")
        }
    }
    println("Run With local scope finished")
}


/*No structured concurrency
* */
suspend fun runWithGlobalScope() {
    GlobalScope.launch {
        println("Launch 1")
        delay(1000)
    }
    GlobalScope.launch{
        delay(2000)
        println("Launch 2")
    }
    println("Run With global scope finished")
}

