package es.heathcliff.ajaniworld.domain.model

import es.heathcliff.ajaniworld.domain.AbstractBaseEntity
import javax.persistence.*

@Entity
@Table(name="spaces_images")
class SpaceImage (
    id: String? = null,
    @ManyToOne
    @JoinColumn(name="spaceId")
    val space: Space,
    @Lob
    @Column(columnDefinition = "image")
    val image : ByteArray
) : AbstractBaseEntity(id)