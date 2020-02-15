package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import javax.persistence.*

@Entity
@Table(name="faults_images")
class FaultImage(
        id: String? = null,
        @ManyToOne
        @JoinColumn(name="faultId")
        var fault: Fault,
        @Lob
        @Column(columnDefinition = "image")
        var image : ByteArray
) : AbstractBaseEntity(id)