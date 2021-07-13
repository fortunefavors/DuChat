import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentFactory
import com.baidu.iov.duchat.util.GsonUtil
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.w3c.dom.Text
import java.io.IOException
import java.lang.reflect.ParameterizedType
import kotlin.concurrent.thread
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Test {
}

 fun main() {
//    println(bf(4))
//    scopeFun()
//    regex()
//    coroutines()
//    delegate()
//    var x = Bb()
//    var b = BaseImpl<Data, Int>()
//    println(x.mRepository.d1)

//    var a : suspend CoroutineScope.() -> String = ::xs
//    delegates()

//    testCoroutine()
//     testFlow()
//     testType()
}

fun testAbsFun() {
    fun getData() = object : AbsFun(){
        override fun fun1(): String {
            return "fun1"
        }

        override fun fun2(): String {
            return "fun2"
        }

        override fun fun3(): String {
            return "fun3"
        }
    }

    getData()


}

abstract class AbsFun {
    protected abstract fun fun1(): String
    protected abstract fun fun2(): String
    protected abstract fun fun3(): String
}

fun testType(){
    var a = Person<String>()
    var b = Data()

    println( a.javaClass.toString())
    println( a.javaClass.genericSuperclass.toString())
}

class Person<T> : BaseImpl<Data, Int>(){
    var address: String by Delegates.vetoable(
        initialValue = "NanJing",
        onChange = { property, oldValue, newValue ->
            println("property: ${property.name}  oldValue: $oldValue  newValue: $newValue")
            return@vetoable newValue == "BeiJing"
        })

    var age: String by MyDelegate()
}

class MyDelegate {
    operator fun <T> getValue(person: Person<T>, property: KProperty<*>): String {
        return "hehe"
    }

    operator fun <T> setValue(person: Person<T>, property: KProperty<*>, s: String) {
        person.age = s
    }

    var value: String? = null

}

fun delegates() {
    val person = Person<String>().apply {
        address = "NanJing"
        age = "11"
    }
//    person.address = "BeiJing"
//    person.address = "ShangHai"
//    person.address = "GuangZhou"
    println("address is ${person.age}")
}

//interface Comparable<in T> {
//    operator fun compareTo(other: T): Int
//}
//fun demo(x: Comparable<Number>) {
//    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
//    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
//    val y: Comparable<Double> = x // OK!
//
//    var aa = 1;
//    val bb : Number = aa
//}
//
//interface Comparable1<out T> {
//    operator fun next() : T
//}
//fun demo2(x: Comparable1<TextView>) {
//    val y: Comparable1<View> = x // 这个没问题，因为 T 是一个 out-参数 // ......
//}
//
//interface Comparable3<in T : String> {
//    operator fun compareTo(other: T): Int
////    operator fun next() : T
//}
//fun demo3(x: Comparable3<*>) {
////   x.compareTo(Button())
////    x.next()
//}

fun <T : Comparable<T>> sort(list: List<T>) {}
fun demo4() {
    var x: Int
    sort(listOf(1, 2, 3))
}

class SG<in T : TextView> {
//    operator fun compareTo(other: T): Int
//    operator fun next() : T
}

fun demo5(x: SG<Button>) {
    var a: Number = 7
//    x.compareTo(a)
    var y: SG<*> = x
}

fun <T> getClass(t: Any): Class<T> {
    // 通过反射 获取父类泛型 (T) 对应 Class类
    println(t.javaClass.genericSuperclass)
    return (t.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0]
            as Class<T>
}

interface Base {
    fun printMessage()
    fun printMessageLine()
}

open class BaseAb<T, U> {
//    val mRepository: T by lazy {
//        getClass<T>(this)
//            .getDeclaredConstructor()
//            .newInstance()
//    }
}

class Data {
    var d1: String = "d1"
    override fun toString(): String {
        return d1
    }
}

open class BaseImpl<T : Data, U>(var x: Int = 6) : BaseAb<T, U>(), Base {
    var name: String = "first"
    override fun printMessage() {
        println(name)
    }

    override fun printMessageLine() {
        println(name)
    }

    init {
//        mRepository.d1
    }

    //中缀函数
    infix fun go(x: Int) {
        var a = x.toString()
        println(a.plus(name))
    }
}

fun BaseImpl<Data, String>.hei(): String {
    return "hello:".plus(name)
}

fun BaseImpl<Data, String>.heh(x: BaseImpl<Data, String>.() -> String): String {
    return x().plus("wow!")
}

class Bb : BaseImpl<Data, Int>(5) {

}

//class Derived(b: Base) : Base by b {
//    override fun printMessage() { println("abc") }
//}


fun delegate() {
//    val b = BaseImpl(10)
//    Derived(b).printMessage()
//    Derived(b).printMessageLine()


//    val answer by lazy {
//        println("Calculating the answer...")
//        42
//    }
//    println("The answer is $answer.")

//    val p: String by Delegate()
//    val x : String by lazy { "" }
//    p = "haha"
//    println(p)

}

class Delegate {
//    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
//return "hello"
//    }

    val value: String
        get() {
            return "我是代理"
        }
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
//        return "$thisRef, thank you for delegating '${property.name}' to me!" }
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) { println("$value has been assigned to '${property.name}' in $thisRef.")
//    }
}

fun foo(bar: Int = 0, baz: Int = 1, qux: (Int, String) -> String) {
    println(qux(3, "hello"))
}

var bf = { p: Int ->
    String
    var x = p + 3
    x.toString()
}

fun String.ext(a: Int): String = this.plus(a.toString())

suspend fun pingBaidu(): Boolean {
    var ret = 0
    withContext(Dispatchers.IO) {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("ping -c 3 www.baidu.com")
            ret = ipProcess.waitFor()
//            Log.i("NetUtils", "exitValue = $ret")
        } catch (e: IOException) {
//            Log.i("NetUtils", "InterruptedException e= " + e.message)
            e.printStackTrace()
        } catch (e: InterruptedException) {
//            Log.i("NetUtils", "InterruptedException e= " + e.message)
            e.printStackTrace()
        }
    }
    return ret == 0
}

fun ping() {
    GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        println("----start----")
        println("pingBaidu:".plus(pingBaidu()))

        println("----end----")
    }
    println("----ui thread go on----")
    Thread.sleep(3000)
}


fun coroutines() {
    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) { // 启动一个新协程并保持对这个作业的引用 delay(1000L)
        println("111111111")
        withContext(Dispatchers.IO) {
            delay(1000)
        }
        println("2222222222")
    }

    println("000000000")
    Thread.sleep(3000)
}

fun scopeFun() {
    val b = BaseImpl<Data, String>(3)
    println(b.heh {
        this.name = "hello"
        "nihao"
    })

    println(b.name)
//    b go 5

    var sb = b.let {
        it.name = "hello!"
        b.printMessage()
        "let"
    }
    println(b.name)
//
//    println(b.name)
//    println(b.x)
//
    var nn = b.run {
        this.name = "hello!"
        printMessage()
        "let"
    }

    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+12fa34-FFFFa-number")) {
        println(match.value)
    }

//    var aa =  b.also {
//        it.name = "also"
//    }
//
//    b.apply {
//        this.name = "apply"
//    }
//
//
}

fun regex() {
    regex1()
}

fun regex1() {
    val emailParttern = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
    val email: String? = emailParttern.find("this is my email mymail@google.com")?.value
    println(email)
    val phoneNumber: String? = Regex(pattern = """\d{3}-\d{3}-\d{4}""")
        .find("phone: 123-456-7890, e..")?.value
    println(phoneNumber)
}

fun x(a: Int) {
    setOnClickListener { view: View, s: String ->
    }
}

var xf = ::x

var onClick = fun(view: View, str: String) {}

fun setOnClickListener(funparam: (view: View, str: String) -> Unit) {
    onClick = funparam

}

fun testCoroutine() {
    runBlocking {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }

}

fun testFlow() {
    MainScope().launch(Dispatchers.IO) {
        try {
            second.take(10)
                .flowOn(Dispatchers.Main)
                .map {
                it % 3600
            }.flowOn(Dispatchers.Default)
            .collect {
                println(it)
            }
        } catch (e: Throwable) {
            println("flow error:" + e.message)
        }

//        repeat(10) {
//            println(getSeconds())
//        }
        println("hahaha" )
    }
    println("hehehe" )

    runCatching { Thread.sleep(10000) }
}

val second: Flow<Int> = flow{
    while (true) {
        var v = getSeconds()
        emit(v)
        delay(500)
    }
}

suspend fun getSeconds(): Int {
    delay(1000)
    return System.currentTimeMillis().toInt()
}



