package com.curso.android.app.ticmas_proyecto_final

import com.curso.android.app.ticmas_proyecto_final.view.MainViewModel
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Before


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialErrorValues() = runTest {
        val value1 = viewModel.inputErrorText1.value
        val value2 = viewModel.inputErrorText2.value
        assertEquals(null, value1)
        assertEquals(null, value2)
    }

    @Test
    fun mainViewModel_TestValidatedText() = runTest {
        val value1 = viewModel.validateString("test", true)
        val value2 = viewModel.validateString("", false)

        assertEquals(true, value1)
        assertEquals(false, value2)
    }

    @Test
    fun mainViewModel_TestCompareEqualTexts() = runTest {
        launch {
            viewModel.compareStrings("ABC", "ABC")
        }
        advanceUntilIdle()
        val result = viewModel.message.value
        assertEquals("Los textos son iguales", result?.message)
    }

    @Test
    fun mainViewModel_TestCompareNoEqualTexts() = runTest {
        launch {
            viewModel.compareStrings("ABC", "BCA")
        }
        advanceUntilIdle()
        val result = viewModel.message.value
        assertEquals("Los textos son distintos", result?.message)
    }
}