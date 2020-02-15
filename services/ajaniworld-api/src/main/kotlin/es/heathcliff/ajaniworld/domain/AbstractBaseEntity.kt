package es.heathcliff.ajaniworld.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.domain.Persistable
import java.util.*
import javax.persistence.*
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
abstract class AbstractBaseEntity(givenId: String? = null) : Persistable<String> {

    @Id
    @Column(name = "id" , columnDefinition="uniqueidentifier")
    private val id: String = givenId ?: UUID.randomUUID().toString()

    @Transient
    private var persisted: Boolean = givenId != null

    override fun getId(): String = id

    override fun isNew(): Boolean = !persisted

    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other == null -> false
            other !is AbstractBaseEntity -> false
            else -> getId() == other.getId()
        }
    }

    @PostPersist
    @PostLoad
    private fun setPersisted() {
        persisted = true
    }
}