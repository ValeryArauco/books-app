package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.domain.Gitalias
import com.ucb.domain.Movie
import com.ucb.framework.dto.AvatarResponseDto
import com.ucb.framework.dto.BookDto
import com.ucb.framework.dto.MovieDto
import com.ucb.framework.persistence.BookEntity
import com.ucb.framework.persistence.GitAccount

fun AvatarResponseDto.toModel(): Gitalias {
    return Gitalias(
        login = login,
        avatarUrl = url
    )
}

fun Gitalias.toEntity(): GitAccount {
    return GitAccount(login)
}

fun GitAccount.toModel(): Gitalias {
    return Gitalias(
        alias,
        ""
    )
}

fun MovieDto.toModel(): Movie {
    return Movie(
        title = title,
        overview = overview,
        posterPath = posterPath
    )
}

fun BookDto.toModel(): Book {
    return Book(
        title = title,
        authors = author,
        publishYear = year.toString()
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        title = title,
        authors = authors.toString(),
        year = publishYear.toString()
    )
}