package model

import model.movie.Movie
import model.movie.Movies
import model.movie.RunningTime
import model.movie.ShowingPeriod
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MoviesTest {
    private val movie1 =
        Movie(
            title = "스파이더맨",
            runningTime = RunningTime(120L),
            showingPeriod =
                ShowingPeriod(
                    startDate = LocalDate.of(2026, 4, 1),
                    endDate = LocalDate.of(2026, 4, 8),
                ),
        )

    @Test
    fun `영화 목록이 비어있으면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Movies(emptyList())
        }
    }

    @Test
    fun `제목으로 영화를 찾을 수 있다`() {
        val movies = Movies(listOf(movie1))

        assertThat(movies.findByTitle("스파이더맨")).isEqualTo(movie1)
    }

    @Test
    fun `존재하지 않는 제목으로 찾으면 null을 반환한다`() {
        val movies = Movies(listOf(movie1))

        assertThat(movies.findByTitle("없는 영화")).isNull()
    }
}
