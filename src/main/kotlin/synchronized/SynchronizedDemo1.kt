package synchronized

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread


object SynchronizedDemo1WithBug {
    private var running = true

    private fun stop() {
        running = false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            while (running) {
            }
        }

        Thread.sleep(1000)
        stop()
    }
}


object SynchronizedDemo1Solution1 {
    @Volatile
    private var running = true

    private fun stop() {
        running = false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            while (running) {
            }
        }

        Thread.sleep(1000)
        stop()
    }
}

object SynchronizedDemo1Solution2 {
    private var running = AtomicBoolean(true)

    private fun stop() {
        running.set(false)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            while (running.get()) {
            }
        }

        Thread.sleep(1000)
        stop()
    }
}