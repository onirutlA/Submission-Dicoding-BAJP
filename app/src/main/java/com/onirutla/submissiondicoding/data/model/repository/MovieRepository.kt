package com.onirutla.submissiondicoding.data.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onirutla.submissiondicoding.data.model.local.MovieEntity
import com.onirutla.submissiondicoding.data.model.remote.MovieResponse
import com.onirutla.submissiondicoding.data.model.remote.RemoteDataSource
import com.onirutla.submissiondicoding.data.source.MovieDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                movieResponse.forEach {
                    val movie = MovieEntity(
                        it.id,
                        it.type,
                        it.title,
                        it.year,
                        it.genre,
                        it.rating,
                        it.description,
                        it.poster,
                        it.trailer
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getDetailMovie(id: String): LiveData<MovieEntity> {
        val movieData = MutableLiveData<MovieEntity>()

        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity

                movieResponse.forEach {
                    if (it.id == id) {
                        movie = MovieEntity(
                            it.id,
                            it.type,
                            it.title,
                            it.year,
                            it.genre,
                            it.rating,
                            it.description,
                            it.poster,
                            it.trailer
                        )
                    }
                }
                movieData.postValue(movie)
            }
        })
        return movieData
    }

    override fun getAllTvShows(): LiveData<List<MovieEntity>> {
        val tvShowResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<MovieResponse>) {
                val tvShowList = ArrayList<MovieEntity>()

                tvShowResponse.forEach {
                    val movie = MovieEntity(
                        it.id,
                        it.type,
                        it.title,
                        it.year,
                        it.genre,
                        it.rating,
                        it.description,
                        it.poster,
                        it.trailer
                    )
                    tvShowList.add(movie)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getDetailTvShow(id: String): LiveData<MovieEntity> {
        val tvShowData = MutableLiveData<MovieEntity>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<MovieResponse>) {
                lateinit var tvShow: MovieEntity

                tvShowResponse.forEach {
                    if (it.id == id) {
                        tvShow = MovieEntity(
                            it.id,
                            it.type,
                            it.title,
                            it.year,
                            it.genre,
                            it.rating,
                            it.description,
                            it.poster,
                            it.trailer
                        )
                    }
                }
                tvShowData.postValue(tvShow)
            }
        })
        return tvShowData
    }
}