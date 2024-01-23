package validation.handler

import exception.DomainException
import validation.ErrorDomain
import validation.ValidationHandler

class ThrowsValidationHandler : ValidationHandler {
    override fun append(errorDomain: ErrorDomain): ValidationHandler {
        throw DomainException(listOf(errorDomain))
    }


    override fun append(handler: ValidationHandler): ValidationHandler {
        val error = handler.getErrors().first()
        throw DomainException.with(listOf(error))
    }

    override fun validate(validation: ValidationHandler.Validation) {
        try {
            validation.validate()
        } catch (exception: Exception) {
            throw DomainException.with(listOf(ErrorDomain(exception.message)))
        }

    }

    override fun getErrors(): List<ErrorDomain> {
        TODO("Not yet implemented")
    }
}