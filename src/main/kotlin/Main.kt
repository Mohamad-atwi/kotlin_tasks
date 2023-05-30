import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    task1()
        val option: Option<String> = "Hello".toOption()
    when (option) {
        is Option.None -> println("Option is None")
        is Option.Some -> println("Option is Some: ${option.value}")
    }
    task3()
}

// Task 1
fun task1() {
    val sentences = arrayOf(
       "Taki mamy klimat",
        "Wszędzie dobrze ale w domu najlepiej",
        "Wyskoczył jak Filip z konopii",
        "Gdzie kucharek sześć tam nie ma co jeść",
        "Nie ma to jak w domu",
        "Konduktorze łaskawy zabierz nas do Warszawy",
        "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
        "Bez pracy nie ma kołaczy",
        "Kto sieje wiatr ten zbiera burzę",
        "Być szybkim jak wiatr",
        "Kopać pod kimś dołki",
        "Gdzie raki zimują",
        "Gdzie pieprz rośnie",
        "Swoją drogą to gdzie rośnie pieprz?",
        "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
        "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
    )

    val wordCountMap = mutableMapOf<String, Int>()

    sentences.flatMap { it.split(" ") }
        .forEach { word ->
            val lowercaseWord = word.toLowerCase()
            wordCountMap[lowercaseWord] = wordCountMap.getOrDefault(lowercaseWord, 0) + 1
        }

    val mostCommonWords = wordCountMap.entries
        .sortedByDescending { it.value }
        .take(3)

    println("The three most common words:")
    mostCommonWords.forEachIndexed { index, (word, count) ->
        println("${index + 1}. \"$word\" - $count")
    }
}

// Task 2
sealed class Option<out T> {
    object None : Option<Nothing>()
    data class Some<T>(val value: T) : Option<T>()

    fun toNullable(): T? = when (this) {
        is Some -> value
        is None -> null
    }
}

fun <T : Any?> T?.toOption(): Option<T> = if (this != null) {
    Option.Some(this)
} else {
    Option.None
}

// Task 3
fun task3() =
    measureTimeMillis {
        runBlocking {
            val deferredResult1 = async { waitAndGetNumber() }
            val deferredResult2 = async { waitAndGetNumber() }
            val sum = deferredResult1.await() + deferredResult2.await()
            println(sum)
        }
    }.also { time ->
        check(time > 1000) { "It runs too quickly - it's not possible" }
        check(time < 2000) { "It runs too slowly" }
        print("Time: $time")
    }

// Don't change this function
suspend fun waitAndGetNumber(): Int {
    delay(1000)
    return (1..10).random()
}
