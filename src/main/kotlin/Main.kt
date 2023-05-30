// use intelliJ or https://play.kotlinlang.org/

// imports for task 3
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    // you can run your functions here

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

    /* TODO Your task is to print to the console the three most common words in the 'sentences' list,
        along with the number of times they occur

            Sample output:
            1. "mam" - 12
            2. "tak" - 5
            3. "z" - 2
    */

    println("Hello World!")
}

// task 2 - Implement functions marked as TODO. After you implement them, use them in main function.
sealed class Option<out T> {
    object None : Option<Nothing>()
    data class Some<T>(val value: T) : Option<T>()

    fun toNullable(): T? =  TODO("This function should return the value if option is Some, or null if option is None")
}

fun <T : Any?> T?.toOption(): Option<T> = TODO("This functon should check if the value is null, and return None if it is, or Some(value) if it is not")

// task 3
fun task3() =
    measureTimeMillis {
        runBlocking {
            // TODO - Change the code below so it runs in less than 2 seconds
            println(waitAndGetNumber() + waitAndGetNumber())
        }
    }.also { time ->
        check(time > 1000) { "It runs too quickly - it's not possible" }
        check(time < 2000) { "It runs too slowly" }
        print("Time: $time")
    }

// don't change this function
suspend fun waitAndGetNumber(): Int {
    delay(1000)
    return (1..10).random()
}