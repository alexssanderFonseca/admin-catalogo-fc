import validation.ValidationHandler

abstract class Entity<ID:Identifier>(
    internal open val id:ID
) {

    abstract fun validate(handler: ValidationHandler)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity<*>) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}