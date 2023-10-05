import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

fun main() {
//    thread()
//    runnable()
//    threadFactory()
//    executor()
//    callable()
}


private fun thread() {
    val thread = object : Thread() {
        override fun run() {
            println("直接重写 Thread 类的 run 方法")
        }
    }
    thread.start()
}

private fun runnable() {
    val runnable = object : Runnable {
        override fun run() {
            println("重写 Runnable 接口的 run 方法")
        }
    }
    val thread = Thread(runnable)
    thread.start()
}

private fun threadFactory() {
    val factory = object : ThreadFactory {
        val count = AtomicInteger(0) // int

        override fun newThread(r: Runnable): Thread =
            Thread(r, "Thread-${count.incrementAndGet()}") // ++count
    }

    val runnable = Runnable {
        println("Thread ${Thread.currentThread().name} start!")
    }

    val threadA = factory.newThread(runnable)
    threadA.start()
    val threadB = factory.newThread(runnable)
    threadB.start()
}

private fun executor() {
    val executor: ExecutorService = Executors.newCachedThreadPool()
    val runnable = Runnable { println("Thread with Runnable started!") }
    executor.execute(runnable)
    executor.execute(runnable)
    executor.execute(runnable)

    val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        /* corePoolSize = */ 2,
        /* maximumPoolSize = */ 4,
        /* keepAliveTime = */ 3, /* unit = */ TimeUnit.SECONDS,
        /* workQueue = */ SynchronousQueue<Runnable>()
    )
    threadPoolExecutor.execute(runnable)
}

private fun callable() {
    val callable = object : Callable<String> {
        override fun call(): String {
            Thread.sleep(5000)
            return "Hello from Callable!"
        }
    }

    val executor = Executors.newCachedThreadPool()
    val future = executor.submit(callable)
    future.get() // 阻塞
}