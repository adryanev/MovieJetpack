package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseRepositoryTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    fun launchTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineRule.testCoroutineScope.launch(testCoroutineRule.testCoroutineDispatcher) { block }

}