package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.repositories.BaseRepositoryTest
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.factory.*
import dev.adryanev.dicoding.moviejetpack.utils.collectData
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.time.ExperimentalTime


@ExperimentalCoroutinesApi
class FavoriteRepositoryTest : BaseRepositoryTest() {

    val repository = mock<FavoriteRepository>()
    val favoriteMovie = createFavoriteMovie()
    val favoriteTvShow = createFavoriteTvShow()

    @ExperimentalTime
    @Test
    fun getFavoriteMovies() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.getFavoriteMovies()).thenReturn(createFavoriteMovieResponse())

            val data = repository.getFavoriteMovies()
            verify(repository).getFavoriteMovies()

            launchTest {
                data.test {
                    val collect = expectItem().collectData()
                    assertNotNull(collect)
                    assertEquals(4, collect.size)
                    assertEquals(favoriteMovie.favorite, collect[0].favorite)
                    assertEquals(
                        favoriteMovie.favorite?.favoriteId!!,
                        collect[0].favorite?.favoriteId
                    )
                    assertEquals(
                        favoriteMovie.favorite?.movieId!!,
                        collect[0].favorite?.movieId
                    )
                    assertEquals(
                        favoriteMovie.favorite?.movieType!!,
                        collect[0].favorite?.movieType
                    )
                    assertEquals(
                        favoriteMovie.favorite?.createdAt!!,
                        collect[0].favorite?.createdAt
                    )

                    assertEquals(favoriteMovie.movie, collect[0].movie)
                    assertEquals(favoriteMovie.movie.id, collect[0].movie.id)
                    assertEquals(favoriteMovie.movie.title, collect[0].movie.title)
                    assertEquals(
                        favoriteMovie.movie.originalTitle,
                        favoriteMovie.movie.originalTitle
                    )
                    assertEquals(
                        favoriteMovie.movie.originalLanguage,
                        favoriteMovie.movie.originalLanguage
                    )
                    assertEquals(
                        favoriteMovie.movie.backdropPath,
                        favoriteMovie.movie.backdropPath
                    )
                    assertEquals(favoriteMovie.movie.posterPath, collect[0].movie.posterPath)
                    assertEquals(favoriteMovie.movie.genreIds, collect[0].movie.genreIds)
                    assertEquals(favoriteMovie.movie.voteAverage, collect[0].movie.voteAverage)
                    assertEquals(favoriteMovie.movie.voteCount, collect[0].movie.voteCount)
                    assertEquals(favoriteMovie.movie.overview, collect[0].movie.overview)
                    assertEquals(favoriteMovie.movie.popularity, collect[0].movie.popularity)
                    assertEquals(favoriteMovie.movie.mediaType, collect[0].movie.mediaType)
                    expectComplete()
                }
            }

        }

    }

    @ExperimentalTime
    @Test
    fun getFavoriteTvShows() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.getFavoriteMovies()).thenReturn(createFavoriteTvShowResponse())

            val data = repository.getFavoriteTvShows()
            verify(repository).getFavoriteTvShows()

            launchTest {
                data.test {
                    val collect = expectItem().collectData()
                    assertNotNull(collect)
                    assertEquals(4, collect.size)
                    assertEquals(favoriteTvShow.favorite, collect[0].favorite)
                    assertEquals(
                        favoriteTvShow.favorite?.favoriteId!!,
                        collect[0].favorite?.favoriteId
                    )
                    assertEquals(
                        favoriteTvShow.favorite?.movieId!!,
                        collect[0].favorite?.movieId
                    )
                    assertEquals(
                        favoriteTvShow.favorite?.movieType!!,
                        collect[0].favorite?.movieType
                    )
                    assertEquals(
                        favoriteTvShow.favorite?.createdAt!!,
                        collect[0].favorite?.createdAt
                    )

                    assertEquals(favoriteTvShow.movie, collect[0].movie)
                    assertEquals(favoriteTvShow.movie.id, collect[0].movie.id)
                    assertEquals(favoriteTvShow.movie.title, collect[0].movie.title)
                    assertEquals(
                        favoriteTvShow.movie.originalTitle,
                        collect[0].movie.originalTitle
                    )
                    assertEquals(
                        favoriteTvShow.movie.originalLanguage,
                        collect[0].movie.originalLanguage
                    )
                    assertEquals(
                        favoriteTvShow.movie.backdropPath,
                        collect[0].movie.backdropPath
                    )
                    assertEquals(favoriteTvShow.movie.posterPath, collect[0].movie.posterPath)
                    assertEquals(favoriteTvShow.movie.genreIds, collect[0].movie.genreIds)
                    assertEquals(favoriteTvShow.movie.voteAverage, collect[0].movie.voteAverage)
                    assertEquals(favoriteTvShow.movie.voteCount, collect[0].movie.voteCount)
                    assertEquals(favoriteTvShow.movie.overview, collect[0].movie.overview)
                    assertEquals(favoriteTvShow.movie.popularity, collect[0].movie.popularity)
                    assertEquals(favoriteTvShow.movie.mediaType, collect[0].movie.mediaType)
                    expectComplete()
                }
            }

        }
    }

    @Test
    fun setMovieFavorite() {
        testCoroutineRule.runBlockingTest {
            val favoriteList = arrayListOf<FavoriteAndMovie>()
            `when`(repository.setMovieFavorite(favoriteMovie, true)).then {
                favoriteList.add(favoriteMovie)
            }
            repository.setMovieFavorite(favoriteMovie, true)
            verify(repository).setMovieFavorite(favoriteMovie, true)
            assertEquals(1, favoriteList.size)

            `when`(repository.setMovieFavorite(favoriteMovie, false)).then {
                favoriteList.remove(favoriteMovie)
            }

            repository.setMovieFavorite(favoriteMovie, false)
            verify(repository).setMovieFavorite(favoriteMovie, false)
            assertEquals(0, favoriteList.size)
        }
    }

    @ExperimentalTime
    @Test
    fun getFavoriteMovieById() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.getFavoriteMovieById(favoriteMovie.movie.id!!)).thenReturn(
                createFavoriteMovieAsFlow()
            )

            val data = repository.getFavoriteMovieById(favoriteMovie.movie.id!!)
            verify(repository).getFavoriteMovieById(favoriteMovie.movie.id!!)

            data.test {
                val collect = expectItem()
                assertNotNull(collect)
                assertEquals(favoriteMovie.favorite, collect.favorite)
                assertEquals(
                    favoriteMovie.favorite?.favoriteId!!,
                    collect.favorite?.favoriteId
                )
                assertEquals(
                    favoriteMovie.favorite?.movieId!!,
                    collect.favorite?.movieId
                )
                assertEquals(
                    favoriteMovie.favorite?.movieType!!,
                    collect.favorite?.movieType
                )
                assertEquals(
                    favoriteMovie.favorite?.createdAt!!,
                    collect.favorite?.createdAt
                )

                assertEquals(favoriteMovie.movie, collect.movie)
                assertEquals(favoriteMovie.movie.id, collect.movie.id)
                assertEquals(favoriteMovie.movie.title, collect.movie.title)
                assertEquals(
                    favoriteMovie.movie.originalTitle,
                    collect.movie.originalTitle
                )
                assertEquals(
                    favoriteMovie.movie.originalLanguage,
                    collect.movie.originalLanguage
                )
                assertEquals(
                    favoriteMovie.movie.backdropPath,
                    collect.movie.backdropPath
                )
                assertEquals(favoriteMovie.movie.posterPath, collect.movie.posterPath)
                assertEquals(favoriteMovie.movie.genreIds, collect.movie.genreIds)
                assertEquals(favoriteMovie.movie.voteAverage, collect.movie.voteAverage)
                assertEquals(favoriteMovie.movie.voteCount, collect.movie.voteCount)
                assertEquals(favoriteMovie.movie.overview, collect.movie.overview)
                assertEquals(favoriteMovie.movie.popularity, collect.movie.popularity)
                assertEquals(favoriteMovie.movie.mediaType, collect.movie.mediaType)
                expectComplete()
            }
        }
    }

    @ExperimentalTime
    @Test
    fun getFavoriteTvShowById() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.getFavoriteMovieById(favoriteTvShow.movie.id!!)).thenReturn(
                createFavoriteTvShowAsFlow()
            )

            val data = repository.getFavoriteMovieById(favoriteTvShow.movie.id!!)
            verify(repository).getFavoriteMovieById(favoriteTvShow.movie.id!!)

            data.test {
                val collect = expectItem()
                assertNotNull(collect)
                assertEquals(favoriteTvShow.favorite, collect.favorite)
                assertEquals(
                    favoriteTvShow.favorite?.favoriteId!!,
                    collect.favorite?.favoriteId
                )
                assertEquals(
                    favoriteTvShow.favorite?.movieId!!,
                    collect.favorite?.movieId
                )
                assertEquals(
                    favoriteTvShow.favorite?.movieType!!,
                    collect.favorite?.movieType
                )
                assertEquals(
                    favoriteTvShow.favorite?.createdAt!!,
                    collect.favorite?.createdAt
                )

                assertEquals(favoriteTvShow.movie, collect.movie)
                assertEquals(favoriteTvShow.movie.id, collect.movie.id)
                assertEquals(favoriteTvShow.movie.title, collect.movie.title)
                assertEquals(
                    favoriteTvShow.movie.originalTitle,
                    collect.movie.originalTitle
                )
                assertEquals(
                    favoriteTvShow.movie.originalLanguage,
                    collect.movie.originalLanguage
                )
                assertEquals(
                    favoriteTvShow.movie.backdropPath,
                    collect.movie.backdropPath
                )
                assertEquals(favoriteTvShow.movie.posterPath, collect.movie.posterPath)
                assertEquals(favoriteTvShow.movie.genreIds, collect.movie.genreIds)
                assertEquals(favoriteTvShow.movie.voteAverage, collect.movie.voteAverage)
                assertEquals(favoriteTvShow.movie.voteCount, collect.movie.voteCount)
                assertEquals(favoriteTvShow.movie.overview, collect.movie.overview)
                assertEquals(favoriteTvShow.movie.popularity, collect.movie.popularity)
                assertEquals(favoriteTvShow.movie.mediaType, collect.movie.mediaType)
                expectComplete()
            }
        }
    }
}