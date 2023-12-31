package cancellation

import kotlinx.coroutines.*

fun main() = runBlocking{
    val launchParent = Job()
    val scope = CoroutineScope(Job())

    val job = scope.launch(launchParent) {
        val j1 = coroutineContext[Job]

        val j2 = launch {
            delay(500)
        }
        println("job passed to the scope.launch as the new context: $launchParent")
        displayChildren(0,launchParent)
        println("job returned from the scope.launch as the new job: $j1")
        displayChildren(0,j1!!)
        println("Job returned from child launch (j2): $j2")
        displayChildren(0,j2)
        j2.join()
    }

    job.join()

}

fun displayChildren(depth:Int = 0,job: Job){
    job.children.forEach {
        for (i in 0..depth){
            println("\t")
        }
        println("child: $it")
        displayChildren(depth+1,it)
    }
}