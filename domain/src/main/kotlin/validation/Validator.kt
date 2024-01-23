package validation

abstract class Validator(
    val handler: ValidationHandler
) {

    protected fun validationHandler(): ValidationHandler = this.handler

    abstract fun validate()
}