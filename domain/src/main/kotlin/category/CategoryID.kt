package category

import Identifier
import java.util.*

class CategoryID private constructor(
    val value: String
) : Identifier() {

    companion object {

        fun unique(): CategoryID {
            return from(UUID.randomUUID())
        }

        fun from(id: String): CategoryID = CategoryID(id)

        private fun from(uuid: UUID) = CategoryID(uuid.toString().lowercase(Locale.getDefault()))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryID) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}