package es.heathcliff.ajaniworld.model.storage

data class StorageItemCreation(val itemName: String, val folder: String?, val content: ByteArray, val mimeType: String){
    val s3Key: String = if(!folder.isNullOrEmpty()){
        "${folder}/${itemName}"
    }else{
        itemName
    }
}