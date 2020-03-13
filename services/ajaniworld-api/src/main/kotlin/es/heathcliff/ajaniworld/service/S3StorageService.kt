package es.heathcliff.ajaniworld.service

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.amazonaws.util.IOUtils
import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.exception.StorageItemNotExistsException
import es.heathcliff.ajaniworld.model.storage.StorageItem
import es.heathcliff.ajaniworld.model.storage.StorageItemCreation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class S3StorageService(@Value("\${ajaniworld.storage.s3.accessKey}") private var accessKey : String,
                       @Value("\${ajaniworld.storage.s3.secretKey}") private var secretKey: String) {

    private val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.EU_WEST_3)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()

    // TODO: Refactor this and return a strong custom model with controlled properties
    fun listObjects(folder: String?, maxItems: Int?) : ObjectListing {
        val listObjectsRequest = ListObjectsRequest(Constants.AJANIWORLD_DEFAULT_S3_BUCKET, folder, null, null, maxItems)

        return s3Client.listObjects(listObjectsRequest)
    }

    fun getObject(bucketName: String, key: String) : StorageItem {
        if(s3Client.doesObjectExist(bucketName, key)){
            val s3Object =  s3Client.getObject(GetObjectRequest(bucketName, key))
            val objectMetadata = s3Object.objectMetadata

            return StorageItem(s3Object.key, objectMetadata.contentType, objectMetadata.lastModified,
                    IOUtils.toByteArray(s3Object.objectContent))
        }else{
            throw StorageItemNotExistsException()
        }
    }

    fun putObject(bucketName: String, storageItemCreation: StorageItemCreation) {
        val byteArrayInputStream = ByteArrayInputStream(storageItemCreation.content)

        val objectMetadata = ObjectMetadata()

        objectMetadata.contentLength = storageItemCreation.content.size.toLong()
        objectMetadata.contentType = storageItemCreation.mimeType

        val putObjectRequest = PutObjectRequest(bucketName,
                storageItemCreation.s3Key, byteArrayInputStream, objectMetadata)

        s3Client.putObject(putObjectRequest)
    }
}