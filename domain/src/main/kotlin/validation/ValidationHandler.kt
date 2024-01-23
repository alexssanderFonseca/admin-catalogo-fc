package validation


interface ValidationHandler {

    fun append(errorDomain: ErrorDomain): ValidationHandler

    fun append(handler: ValidationHandler): ValidationHandler

    fun validate(validation: Validation)

    fun getErrors(): List<ErrorDomain>

    fun hasErrors() = getErrors().isNotEmpty()

    interface Validation {
        fun validate()
    }
}