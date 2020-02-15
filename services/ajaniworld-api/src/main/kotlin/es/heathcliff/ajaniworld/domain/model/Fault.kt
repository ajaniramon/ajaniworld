package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name="faults")
class Fault (
        id: String? = null,
        @Column(name="date", nullable = false)
        var date: Date,
        @Column(name="description", nullable = false, length = 255)
        var description: String,
        @ManyToOne
        @JoinColumn(name="spaceId")
        var space: Space
) : AbstractBaseEntity(id)