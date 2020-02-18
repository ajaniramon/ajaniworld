package es.heathcliff.ajaniworld.model.storage

import java.util.*

data class StorageItem(val key: String, val mimeType : String, val lastModified: Date,
                       val content: ByteArray){

}