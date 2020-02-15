package es.heathcliff.ajaniworld.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "roles")
@JsonIgnoreProperties
class Role (id: String?,
            @Column(name = "name", nullable = false, length = 16, unique = true)
            var name: String?,
            @Column(name="description", nullable = true, length=24)
            val description: String?,
            @ManyToMany(mappedBy = "roles")
            @JsonIgnoreProperties("roles")
            var users: MutableList<User>) : AbstractBaseEntity(id)