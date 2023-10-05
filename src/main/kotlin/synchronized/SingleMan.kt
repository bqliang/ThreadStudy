package synchronized

class SingleBoy private constructor() {
    companion object {
        private var instance: SingleBoy? = null

        fun getInstance(): SingleBoy {
            return instance ?: SingleBoy().also { instance = it }
        }
    }
}

class SingleMan private constructor() {
    companion object {
        @Volatile
        private var instance: SingleMan? = null

        fun getInstance(): SingleMan = instance ?: synchronized(this) {
            instance ?: SingleMan().also { instance = it }
        }
    }
}