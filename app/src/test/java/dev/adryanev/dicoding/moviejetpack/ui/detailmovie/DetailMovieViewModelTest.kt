package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.factory.createMovie
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class DetailMovieViewModelTest: BaseViewModelTest() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movie: Movie = createMovie()

}