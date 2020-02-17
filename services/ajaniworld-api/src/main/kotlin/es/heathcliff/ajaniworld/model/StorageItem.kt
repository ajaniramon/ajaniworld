package es.heathcliff.ajaniworld.model

data class StorageItem(val exists: Boolean, val key: String?, val content: ByteArray?){
    constructor(exists: Boolean) : this(exists, null, null) {

    }
}