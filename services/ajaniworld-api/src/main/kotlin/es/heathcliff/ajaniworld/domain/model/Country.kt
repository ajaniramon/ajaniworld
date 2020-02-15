package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "countries")
class Country (
        id: String? = null,
        @Column(name = "name", length = 32, unique = true)
        var name: String?,
        @Lob
        @Column(columnDefinition = "image")
        var flag : ByteArray?,
        @OneToMany
        @JoinColumn(name="countryId")
        var states: Set<State>
) : AbstractBaseEntity(id)