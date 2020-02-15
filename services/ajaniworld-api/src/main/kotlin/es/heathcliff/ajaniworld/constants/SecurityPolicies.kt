package es.heathcliff.ajaniworld.constants

object SecurityPolicies {
    const val ALLOW_ONLY_ADMIN = "hasAnyAuthority('ADMIN')"
    const val ALLOW_REGISTRED_USER = "hasAnyAuthority('USER','ADMIN')"
}