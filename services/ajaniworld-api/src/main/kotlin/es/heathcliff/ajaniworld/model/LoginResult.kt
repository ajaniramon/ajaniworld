package es.heathcliff.ajaniworld.model

data class LoginResult(val success: Boolean, val token: String?, val isAdmin: Boolean) {
}