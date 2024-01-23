package category

import validation.ErrorDomain
import validation.ValidationHandler
import validation.Validator

private const val MIN_LENGTH = 3

private const val MAX_LENGTH = 255

class CategoryValidator(
    handler: ValidationHandler,
    private val category: Category
) : Validator(handler = handler) {

    override fun validate() {
        checkNameConstraints()
    }

    private fun checkNameConstraints() {
        val name = category.name.trim()
        if (name.isEmpty()) {
            this.validationHandler()
                .append(ErrorDomain("'name' should not be empty"))
            return
        }
        if (name.length < MIN_LENGTH || name.trim().length > MAX_LENGTH) {
            this.validationHandler()
                .append(ErrorDomain("'name' must be between 3 and 255 characters"))
            return
        }
    }
}