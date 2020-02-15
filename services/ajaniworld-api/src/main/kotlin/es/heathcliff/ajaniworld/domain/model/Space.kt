package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name="spaces")
class Space(id: String?,
            @Column(name = "name", length = 64)
            var name: String,
            @Column(name = "address", length = 128)
            var address: String,
            @Column(name = "city", length = 32)
            var city: String,
            @Column(name = "zipCode", length = 12)
            var zipCode: String,
            @ManyToOne
            @JoinColumn(name = "stateId")
            var state: State
            ) : AbstractBaseEntity(id)