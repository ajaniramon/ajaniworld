package es.heathcliff.ajaniworld.util

fun buildSpaceImageUrl(distributionUrl: String, s3Key: String) : String {
    return distributionUrl + s3Key
}