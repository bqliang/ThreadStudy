package synchronized

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

object SynchronizedDemo2WithBug {

    private var x = 0

    private fun count() {
        x++
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = $x")
        }

        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = $x")
        }
    }
}

object SynchronizedDemo2Solution1 {

    private var x = 0

    @Synchronized
    private fun count() {
        x++
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = $x")
        }

        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = $x")
        }
    }
}

object SynchronizedDemo2Solution2 {

    private var x = AtomicInteger(0)

    private fun count() {
        x.incrementAndGet()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = ${x.get()}")
        }

        thread {
            repeat(1_000_000) {
                count()
            }
            println("x = ${x.get()}")
        }
    }
}