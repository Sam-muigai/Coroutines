package dispatchers

import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
val executor: ExecutorService = Executors.newFixedThreadPool(10)

fun main() = runBlocking {
    val jobs = arrayListOf<Job>()

    jobs += launch {
        doWork("  Main")
    }
    jobs += launch(Dispatchers.Default) {
        doWork("  Default")
    }
    jobs += launch(Dispatchers.IO) {
        doWork("  IO")
    }
    jobs += launch(Dispatchers.Unconfined) {
        doWork("  Unconfined")
    }
    jobs += launch(dispatcher) {
        doWork("  cachedThreadPool")
    }
    jobs += launch(executor.asCoroutineDispatcher()) {
        doWork("  fixedThreadPool")
    }


    jobs.forEach{
        it.join()
    }

    println()
    println()
    println()

    val job1 = launch (Dispatchers.IO){
        val jobs = arrayListOf<Job>()
        jobs += launch {
            println("  'default' : In thread ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.Default) {
            println("  'defaultDispatchers' : In thread ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.IO) {
            println("  'IO Dispatchers' : In thread ${Thread.currentThread().name}")
        }
        jobs += launch(Dispatchers.Unconfined) {
            println("  'Unconfined Dispatchers' : In thread ${Thread.currentThread().name}")
        }
        jobs += launch(dispatcher) {
            println(" 'cachedThreadPool': In thread ${Thread.currentThread().name}")
        }
        jobs += launch(executor.asCoroutineDispatcher()) {
            println(" 'fixedThreadPool': In thread ${Thread.currentThread().name}")
        }
    }

    job1.join()

    executor.shutdownNow()
    dispatcher.close()
}


suspend fun doWork(dispatcherName : String){
    withContext(Dispatchers.IO){
        println("caller is running in $dispatcherName: (But) Running on thread :${Thread.currentThread().name}")
    }
}

























