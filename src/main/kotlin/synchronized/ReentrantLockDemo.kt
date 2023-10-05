package synchronized

import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock

object ReentrantLockDemo {

    private var count = 0

    private val reentrantLock = ReentrantLock()

    fun count() {
        reentrantLock.lock()
        try {
            count++
            // some other operations
        } finally {
            reentrantLock.unlock()
        }
    }
}


object ReentrantReadWriteLockDemo {

    private var count = 0

    private val readWriteLock = ReentrantReadWriteLock()
    private val writeLock = readWriteLock.writeLock()
    private val readLock = readWriteLock.readLock()

    fun count() {
        writeLock.lock()
        try {
            count++
            // some other operations
        } finally {
            writeLock.unlock()
        }
    }

    fun print() {
        readLock.lock()
        try {
            println("count = $count")
        } finally {
            readLock.unlock()
        }
    }
}