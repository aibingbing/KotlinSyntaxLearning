package net.betterbing.learn


// 这是单行注释
/*
 * 这是
 * 多行注释
 * 多行注释
 * 多行注释
 */

/**
 * 从kotlin1.3开始，main方法可以不用传args参数
 */
fun main(args: Array<String>) {
    // java 中的final类型，不能修改
    val fooVal = 10

    var fooVar = 10
    fooVar = 20 //var 类型变量可以重新赋值

    /*
    * 大多数时候kotlin可以检测到变量的类型
    * */
    val foo: Int = 8
    val foo1 = 8

    /*
    * kotlin中string和java中类似
    * */
    val fooString = "My String Is Here!"
    val barString = "Printing on a new line?\nNo Problem!"
    val bazString = "Do you want to add a tab?\tNo Problem!"
    println(fooString)
    println(barString)
    println(bazString)
    println(" fooVar:$fooVal  fooVal:$fooVal") //可以在string中用$引用变量、函数或者表达式

    /*
     * 原始字符串用""" """包含
     */
    val fooRawString = """
fun helloWorld(val name : String) {
   println("Hello, world!")
}
"""
    println(fooRawString)

    val fooTemplateString = "$fooString has ${fooString.length} characters"
    println(fooTemplateString) // => My String Is Here! has 18 characters

    /*
      可能为null的变量定义的时候要在类型后加？，比如String?,引用的时候也需要使用?.来引用，比如a?.length
      对于可能为空的变量使用?:来选择返回值，类似java中三元运算符
     */
    var fooNullable: String? = "abc"
    println(fooNullable?.length) // => 3
    println(fooNullable?.length ?: -1) // => 3
    fooNullable = null
    println(fooNullable?.length) // => null
    println(fooNullable?.length ?: -1) // => -1


    /*
     使用fun来定义一个方法，方法的参数在fun()的括号中定义，可以定义默认值
     */
    fun hello(name: String = "world"): String {
        return "Hello, $name!"
    }
    println(hello("foo")) // => Hello, foo!
    println(hello(name = "bar")) // => Hello, bar!
    println(hello("bar")) // => Hello, bar!
    println(hello()) // => Hello, world!

    /*
      使用vararg允许传递多个参数给方法
     */
    fun varargExample(vararg names: Int) {
        println("Argument has ${names.size} elements")
    }
    varargExample() // => Argument has 0 elements
    varargExample(1) // => Argument has 1 elements
    varargExample(1, 2, 3) // => Argument has 3 elements

    /*
     方法只有一行的时候可以省略{}
     */
    fun odd(x: Int): Boolean = x % 2 == 1
    println(odd(6)) // => false
    println(odd(7)) // => true

    /*
      当fun 返回值可以推测出来的时候可以省略
     */
    fun even(x: Int) = x % 2 == 0
    println(even(6)) // => true
    println(even(7)) // => false

    /*
     方法可以作为一个参数
     */
    fun not(f: (Int) -> Boolean): (Int) -> Boolean {
        return { n -> !f.invoke(n) }
    }

    val notOdd = not(::odd)
    val notEven = not(::even)
    // 匿名表达式可以作为一个参数
    val notZero = not { n -> n == 0 }
    val notPositive = not { it > 0 }
    for (i in 0..4) {
        println("${notOdd(i)} ${notEven(i)} ${notZero(i)} ${notPositive(i)}")
    }

    /*
     使用class定义一个类
     */
    class ExampleClass(val x: Int) {
        fun memberFunction(y: Int): Int {
            return x + y
        }

        infix fun infixMemberFunction(y: Int): Int {
            return x * y
        }
    }

    val fooExampleClass = ExampleClass(7)
    // 使用.引用一个方法
    println(fooExampleClass.memberFunction(4)) // => 11
    // infix 标示的方法可以使用infix 语法调用
    println(fooExampleClass infixMemberFunction (4)) // => 28
    println(fooExampleClass infixMemberFunction 4) // => 28

    /*
      data classes 只存储数据，hashCode/equals/toString 自动生成
     */
    data class DataClassExample(val x: Int, val y: Int, val z: Int)

    val fooData = DataClassExample(1, 2, 4)
    println(fooData) // => DataClassExample(x=1, y=2, z=4)
    /*
     data classes 有一个copy方法
     */
    val fooCopy = fooData.copy(y = 100)
    println(fooCopy) // => DataClassExample(x=1, y=100, z=4)

    // Objects can be destructured into multiple variables.
    val (a, b, c) = fooCopy
    println("$a $b $c") // => 1 100 4

    for ((a, b, c) in listOf(fooData, fooCopy)) {
        println("$a $b $c") // => 1 100 4
    }
    val mapData = mapOf("a" to 1, "b" to 2)
    for ((key, value) in mapData) {
        println("$key -> $value")
    }

    data class MutableDataClassExample(var x: Int, var y: Int, var z: Int)

    val fooMutableData = MutableDataClassExample(7, 4, 9)
    with(fooMutableData) {
        x -= 2
        y += 2
        z--
    }
    println(fooMutableData) // => MutableDataClassExample(x=5, y=6, z=8)

    val fooList = listOf("a", "b", "c")
    println(fooList.size) // => 3
    println(fooList.first()) // => a
    println(fooList.last()) // => c
    // Elements of a list can be accessed by their index.
    println(fooList[1]) // => b

    // A mutable list can be created using the "mutableListOf" function.
    val fooMutableList = mutableListOf("a", "b", "c")
    fooMutableList.add("d")
    println(fooMutableList.last()) // => d
    println(fooMutableList.size) // => 4

    // We can create a set using the "setOf" function.
    val fooSet = setOf("a", "b", "c")
    println(fooSet.contains("a")) // => true
    println(fooSet.contains("z")) // => false

    // We can create a map using the "mapOf" function.
    val fooMap = mapOf("a" to 8, "b" to 7, "c" to 9)
    // Map values can be accessed by their key.
    println(fooMap["a"]) // => 8

    /*
      Sequences represent lazily-evaluated collections.
      We can create a sequence using the "generateSequence" function.
    */
    val fooSequence = generateSequence(1, { it + 1 })
    val x = fooSequence.take(10).toList()
    println(x) // => [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    // An example of using a sequence to generate Fibonacci numbers:
    fun fibonacciSequence(): Sequence<Long> {
        var a = 0L
        var b = 1L

        fun next(): Long {
            val result = a + b
            a = b
            b = result
            return a
        }

        return generateSequence(::next)
    }

    val y = fibonacciSequence().take(10).toList()
    println(y) // => [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]

    // Kotlin provides higher-order functions for working with collections.
    val z = (1..9).map { it * 3 }
        .filter { it < 20 }
        .groupBy { it % 2 == 0 }
        .mapKeys { if (it.key) "even" else "odd" }
    println(z) // => {odd=[3, 9, 15], even=[6, 12, 18]}

    // A "for" loop can be used with anything that provides an iterator.
    for (c in "hello") {
        println(c)
    }

    // "while" loops work in the same way as other languages.
    var ctr = 0
    while (ctr < 5) {
        println(ctr)
        ctr++
    }
    do {
        println(ctr)
        ctr++
    } while (ctr < 10)

    /*
   "if" can be used as an expression that returns a value.
   For this reason the ternary ?: operator is not needed in Kotlin.
   */
    val num = 5
    val message = if (num % 2 == 0) "even" else "odd"
    println("$num is $message") // => 5 is odd

    // "when" can be used as an alternative to "if-else if" chains.
    val i = 10
    when {
        i < 7 -> println("first block")
        fooString.startsWith("hello") -> println("second block")
        else -> println("else block")
    }

    // "when" can be used with an argument.
    when (i) {
        0, 21 -> println("0 or 21")
        in 1..20 -> println("in the range 1 to 20")
        else -> println("none of the above")
    }

    // "when" can be used as a function that returns a value.
    var result = when (i) {
        0, 21 -> "0 or 21"
        in 1..20 -> "in the range 1 to 20"
        else -> "none of the above"
    }
    println(result)

    /*
    We can check if an object is a particular type by using the "is" operator.
    If an object passes a type check then it can be used as that type without
    explicitly casting it.
    */
    fun smartCastExample(x: Any): Boolean {
        if (x is Boolean) {
            // x is automatically cast to Boolean
            return x
        } else if (x is Int) {
            // x is automatically cast to Int
            return x > 0
        } else if (x is String) {
            // x is automatically cast to String
            return x.isNotEmpty()
        } else {
            return false
        }
    }
    println(smartCastExample("Hello, world!")) // => true
    println(smartCastExample("")) // => false
    println(smartCastExample(5)) // => true
    println(smartCastExample(0)) // => false
    println(smartCastExample(true)) // => true

    // Smartcast also works with when block
    fun smartCastWhenExample(x: Any) = when (x) {
        is Boolean -> x
        is Int -> x > 0
        is String -> x.isNotEmpty()
        else -> false
    }
    println(smartCastWhenExample("Hello, world!")) // => true
    println(smartCastWhenExample("")) // => false
    println(smartCastWhenExample(5)) // => true
    println(smartCastWhenExample(0)) // => false
    println(smartCastWhenExample(true)) // => true

    /*
        Extensions are a way to add new functionality to a class.
        This is similar to C# extension methods.
        */
    fun String.remove(c: Char): String {
        return this.filter { it != c }
    }
    println("Hello, world!".remove('l')) // => Heo, word!

    println(EnumExample.A) // => A
    println(ObjectExample.hello()) // => hello

    testOperator()
}

// Enum classes are similar to Java enum types.
enum class EnumExample {
    A, B, C
}

/*
The "object" keyword can be used to create singleton objects.
We cannot instantiate it but we can refer to its unique instance by its name.
This is similar to Scala singleton objects.
*/
object ObjectExample {
    fun hello(): String {
        return "hello"
    }
}

fun useObject() {
    ObjectExample.hello()
    val someRef: Any = ObjectExample // we use objects name just as is
}

/* The not-null assertion operator (!!) converts any value to a non-null type and
throws an exception if the value is null.
*/
var b: String? = "abc"
val l = b!!.length

/* You can add many custom operations using symbol like +, to particular instance
by overloading the built-in kotlin operator, using "operator" keyword

below is the sample class to add some operator, and the most basic example
*/
data class SomeClass(var savedValue: Int = 0)

// instance += valueToAdd
operator fun SomeClass.plusAssign(valueToAdd: Int) {
    this.savedValue += valueToAdd
}

// -instance
operator fun SomeClass.unaryMinus() = SomeClass(-this.savedValue)

// ++instance or instance++
operator fun SomeClass.inc() = SomeClass(this.savedValue + 1)

// instance * other
operator fun SomeClass.times(other: SomeClass) =
    SomeClass(this.savedValue * other.savedValue)

// an overload for multiply
operator fun SomeClass.times(value: Int) = SomeClass(this.savedValue * value)

// other in instance
operator fun SomeClass.contains(other: SomeClass) =
    other.savedValue == this.savedValue

// instance[dummyIndex] = valueToSet
operator fun SomeClass.set(dummyIndex: Int, valueToSet: Int) {
    this.savedValue = valueToSet + dummyIndex
}

// instance()
operator fun SomeClass.invoke() {
    println("instance invoked by invoker")
}

/* return type must be Integer,
so that, it can be translated to "returned value" compareTo 0

for equality (==,!=) using operator will violates overloading equals function,
since it is already defined in Any class
*/
operator fun SomeClass.compareTo(other: SomeClass) =
    this.savedValue - other.savedValue

fun testOperator() {
    var x = SomeClass(4)

    println(x) // => "SomeClass(savedValue=4)"
    x += 10
    println(x) // => "SomeClass(savedValue=14)"
    println(-x) // => "SomeClass(savedValue=-14)"
    println(++x) // => "SomeClass(savedValue=15)"
    println(x * SomeClass(3)) // => "SomeClass(savedValue=45)"
    println(x * 2) // => "SomeClass(savedValue=30)"
    println(SomeClass(15) in x) // => true
    x[2] = 10
    println(x) // => "SomeClass(savedValue=12)"
    x() // => "instance invoked by invoker"
//    println(x >= 15) // => false
}