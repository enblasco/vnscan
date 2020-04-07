package es.verdnatura.vnsplits.data.exceptions

/**
 * Created by Enrique Blasco Blanquer on 1/3/17.
 */
class ServerException(override var message: String) : RuntimeException(message)