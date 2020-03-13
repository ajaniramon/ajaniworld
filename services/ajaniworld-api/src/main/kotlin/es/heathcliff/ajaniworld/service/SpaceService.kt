package es.heathcliff.ajaniworld.service

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.Constants.ERROR_NOT_EXISTS
import es.heathcliff.ajaniworld.constants.Constants.SPACE_FIELD_NAME
import es.heathcliff.ajaniworld.constants.Constants.SPACE_IMAGE_FIELD_NAME
import es.heathcliff.ajaniworld.domain.model.Space
import es.heathcliff.ajaniworld.domain.model.SpaceImage
import es.heathcliff.ajaniworld.exception.ColivingError
import es.heathcliff.ajaniworld.exception.ColivingException
import es.heathcliff.ajaniworld.mapper.map
import es.heathcliff.ajaniworld.mapper.mapFrom
import es.heathcliff.ajaniworld.model.SpaceDto
import es.heathcliff.ajaniworld.model.SpaceImageDto
import es.heathcliff.ajaniworld.model.storage.StorageItemCreation
import es.heathcliff.ajaniworld.repository.SpaceImageRepository
import es.heathcliff.ajaniworld.repository.SpaceRepository
import es.heathcliff.ajaniworld.util.buildSpaceImageUrl
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SpaceService(
        @Value("\${ajaniworld.distribution.cloudfront.url}")
        private var distributionUrl: String,
        private var storageService: S3StorageService,
        private var spaceRepository: SpaceRepository,
        private var spaceImageRepository: SpaceImageRepository) {
    fun findAll() : List<SpaceDto> = spaceRepository.findAll().map(::map)

    fun save(spaceDto: SpaceDto){
        spaceRepository.save(mapFrom(spaceDto))
    }

    fun setStarredImage(spaceId: String, imageId: String){
        val space = spaceRepository.findByIdOrNull(spaceId)
        val image = spaceImageRepository.findByIdOrNull(imageId)

        if(space == null){
            throw ColivingException(listOf(ColivingError(SPACE_FIELD_NAME, ERROR_NOT_EXISTS)))
        }

        if(image == null){
            throw ColivingException(listOf(ColivingError(SPACE_IMAGE_FIELD_NAME, ERROR_NOT_EXISTS)))
        }

        space.starredImage = image

        spaceRepository.save(space)
    }

    fun addImages(spaceId: String, images: List<StorageItemCreation>){
        val space = spaceRepository.findByIdOrNull(spaceId)

        if(space == null){
            throw ColivingException(listOf(ColivingError(SPACE_FIELD_NAME, ERROR_NOT_EXISTS)))
        }else{
            images.forEach {
                storageService.putObject(Constants.COLIVING_SPACES_S3_BUCKET, it)
                addImageToSpace(space, buildSpaceImageUrl(distributionUrl, it.s3Key))
            }
        }
    }

    private fun addImageToSpace(space: Space, url: String) {
        if(!spaceImageRepository.existsBySpace_IdAndImage(space.id, url)){
            val spaceImage = SpaceImage(null, space, url)

            spaceImageRepository.save(spaceImage)
        }
    }
}