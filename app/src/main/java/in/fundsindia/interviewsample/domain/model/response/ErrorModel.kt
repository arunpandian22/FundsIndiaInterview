package `in`.fundsindia.interviewsample.domain.model.response

/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
data class ErrorModel(
        var message: String?,
        var code: Int?,
         var errorResponse: String
) {

    constructor(errorStatus: String) : this(null, null, errorStatus)
}