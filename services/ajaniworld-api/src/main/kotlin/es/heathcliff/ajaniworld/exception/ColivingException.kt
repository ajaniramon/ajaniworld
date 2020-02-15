package es.heathcliff.ajaniworld.exception

class ColivingException(errors: List<ColivingError>) : Exception() {
    var errors : List<ColivingError>? = null

    init {
        this.errors = errors
    }
}