package com.hardik.playground

import android.app.IntentService
import android.content.Intent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test

import org.junit.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit [TestRule] that sets the Main dispatcher to [testDispatcher]
 * for the duration of the test.
 */
class MainDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UnitTests {
    @ExperimentalCoroutinesApi
    val testDispatcher: MainDispatcherRule = MainDispatcherRule()


    @Test
    fun addition_isCorrect() {

        test_me()
        assertEquals(4, 2 + 2)
    }

    @Test
    fun when_collected_flow_multiple_time_then_return_same_values() = runTest {
        ensureActive()
        val coldStream = flow {
            for (i in 1..5) {
                delay(100L)
                emit(i)
            }
            delay(500000)
        }
        val collect1 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        val collect2 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        assertEquals("1, 2, 3, 4, 5", collect1)
        assertEquals("1, 2, 3, 4, 5", collect2)

    }

    fun test_me() = runBlocking {
        flowOf(1, 2, 3)
            .onEach { delay(100) }
            .collect { println(it) }

        flowOf("a", "b", "c")
            .collect { println(it) }

    }

    @Test
    fun given_channel_when_pass_data_from_one_coroutine_then_receive_in_another() = runBlocking {
        val channel = Channel<Int>()
        launch(Dispatchers.IO) { //
            println("launch :: ${Thread.currentThread().name}")// coroutine #1
            for (i in 1..5) {
                delay(100L)
                channel.send(i)
            }
            channel.close()
        }
        val result = async {
            println("async :: ${Thread.currentThread().name}")// coroutine #1
            // coroutine #2
            buildString {
                channel.consumeEach {
                    append(it).append(", ")
                }
            }.removeSuffix(", ")
        }
        result.await()
        assertEquals("1, 2, 3, 4, 5", result.await())
    }

    @Test
    fun parallel_api() = runBlocking {
        val job1 = async {
            delay(1000)
            "result"
        }
        val job2 = async {
            delay(5500)
            "again"
        }
        val result = awaitAll(job1, job2)

        assertEquals(result, listOf("result", "again"))
    }

    @Test
    fun throw_exception() = runBlocking(context = CoroutineExceptionHandler { coroutineContext, throwable -> println(throwable) }) {
        var result = 0
        supervisorScope {
            val a = async() {
                delay(500)
                if (1 == 1) {
                    throw Exception("exception a")
                }
                2
            }
            val b = async() {
                delay(500)
                3
            }
            try {
                result = a.await() + b.await()
                println("result is $result")
            } catch (e: Exception) {
                println("exception: ${e.message}")
            }
        }

        assertEquals(result, 5)

    }

    suspend fun expensiveComputation(res: MutableList<String>) {
        delay(1000L)
        res.add("word!")
    }

    @Test
    fun givenAsyncCoroutine_whenStartIt_thenShouldExecuteItInTheAsyncWay() {
        //given
        val res = mutableListOf<String>()

        //when
        runBlocking {
            val job = launch { expensiveComputation(res) }
            res.add("Hello,")
        }

        //then
        assertEquals(res, listOf("Hello,", "word!"))
    }
}


suspend fun fetchLatestNews(): List<String> {
    val data = withContext(Dispatchers.IO) {
        return@withContext listOf("","")
    }
    return data
}