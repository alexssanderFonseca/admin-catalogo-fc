package category

import AggregateRoot
import validation.ValidationHandler
import java.time.Instant

class Category(
    id: CategoryID = CategoryID.unique(),
    var name: String,
    var description: String,
    var isActive: Boolean,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
    var deletedAt: Instant? = if (!isActive) Instant.now() else null
) : AggregateRoot<CategoryID>(id = id) {

    override fun validate(handler: ValidationHandler) {
        CategoryValidator(handler, this)
            .validate()
    }

    fun update(name: String, description: String, isActive: Boolean) {
        this.name = name
        this.description = description
        this.isActive = isActive
        if (isActive) active() else disable()
    }

    fun active() {
        this.deletedAt = null;
        this.updatedAt = Instant.now()
        this.isActive = true
    }

    fun disable() {
        if (deletedAt == null) {
            this.deletedAt = Instant.now()
        }
        this.isActive = false
        this.updatedAt = Instant.now()
    }
}