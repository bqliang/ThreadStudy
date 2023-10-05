package synchronized

object SynchronizedDemo3WithBug {

    private var a = 0
    private var b = 0

    private var isPlaying = true

    @Synchronized
    fun setValues(value: Int) {
        a = value
        b = value
    }

    @Synchronized
    fun setPlaying(isPlaying: Boolean) {
        this.isPlaying = isPlaying
    }
}

object SynchronizedDemo3Solution {

    private var a = 0
    private var b = 0

    private var isPlaying = true

    private val lock1 = Any()
    private val lock2 = Any()

    fun setValues(value: Int) {
        synchronized(lock1) {
            a = value
            b = value
        }
    }

    fun setPlaying(isPlaying: Boolean) {
        synchronized(lock2) {
            this.isPlaying = isPlaying
        }
    }
}