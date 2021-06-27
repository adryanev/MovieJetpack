package dev.adryanev.dicoding.moviejetpack.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }


    protected fun launchTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineRule.testCoroutineScope.launch(testCoroutineRule.testCoroutineDispatcher) { block }

}