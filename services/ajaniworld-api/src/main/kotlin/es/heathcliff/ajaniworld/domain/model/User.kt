package es.heathcliff.ajaniworld.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonIgnoreProperties
class User(
        id: String? = null,
        @Column(name = "code", length = 20, unique = true, nullable = false)
        var code: String?,
        @Column(name = "name", length = 20, nullable = false)
        var name: String?,
        @Column(name = "surnames", length = 80, nullable = false)
        var surnames: String?,
        @Column(name = "email", length = 156, unique = true, nullable = false)
        var email: String?,
        @Column(name = "phone", length = 32, nullable = false)
        var phone: String?,
        @Column(name = "password", length = 64, nullable = false)
        var password : String?,
        @Lob
        @Column(columnDefinition = "image", nullable = true)
        var avatar : ByteArray?,
        @ManyToMany
        @JoinTable(name = "users_roles")
        @JsonIgnoreProperties("users")
        var roles : MutableList<Role>
) : AbstractBaseEntity(id)