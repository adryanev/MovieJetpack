package dev.adryanev.dicoding.moviejetpack.utils

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T> spy(): T = Mockito.spy(T::class.java)
