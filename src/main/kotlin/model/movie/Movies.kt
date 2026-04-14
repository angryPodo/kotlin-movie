package model.movie

class Movies(
    private val value: List<Movie>,
) {
    init {
        require(value.isNotEmpty()) { "영화 목록이 비어있으면 안됩니다." }
    }

    fun findByTitle(title: String): Movie? = value.find { it.title == title }
}
