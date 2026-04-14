package model

import model.movie.ShowingPeriod
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ShowingPeriodTest {
    @Test
    fun `시작일이 종료일보다 이후일 경우 예외가 발생한다`() {
        // given
        val startDate = LocalDate.of(2026, 4, 1)
        val endDate = startDate.minusYears(1)

        // when & then
        assertThrows(IllegalArgumentException::class.java) {
            ShowingPeriod(
                startDate = startDate,
                endDate = endDate,
            )
        }
    }

}
