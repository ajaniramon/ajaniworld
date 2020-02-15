package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "states")
class State (
        id: String?,
        @Column(name ="name", length = 64)
        var name: String,
        @ManyToOne
        @JoinColumn(name="countryId")
        var country: Country
) : AbstractBaseEntity(id)