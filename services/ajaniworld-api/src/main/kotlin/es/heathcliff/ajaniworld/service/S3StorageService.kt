package es.heathcliff.ajaniworld.service

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.amazonaws.util.IOUtils
import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.model.StorageItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class S3StorageService(@Value("\${ajaniworld.storage.s3.accessKey}") private var accessKey : String,
                       @Value("\${ajaniworld.storage.s3.secretKey}") private var secretKey: String) {

    private val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()

    // TODO: Refactor this and return a strong custom model with controlled properties
    fun listObjects(folder: String?, maxItems: Int?) : ObjectListing {
        val listObjectsRequest = ListObjectsRequest(Constants.AJANIWORLD_DEFAULT_S3_BUCKET, folder, null, null, maxItems)

        return s3Client.listObjects(listObjectsRequest)
    }

    fun getObject(key: String) : StorageItem {
        if(s3Client.doesObjectExist(Constants.AJANIWORLD_DEFAULT_S3_BUCKET, key)){
            val s3Object =  s3Client.getObject(GetObjectRequest(Constants.AJANIWORLD_DEFAULT_S3_BUCKET, key))

            return StorageItem(true, s3Object.key, IOUtils.toByteArray(s3Object.objectContent))
        }else{
            return StorageItem(false)
        }
    }
}