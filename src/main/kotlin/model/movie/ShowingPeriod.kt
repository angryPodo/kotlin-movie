package model.movie

import java.time.LocalDate

data class ShowingPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    init {
        require(startDate <= endDate) { "시작일이 종료일보다 이후일 수 없습니다." }
    }

}
