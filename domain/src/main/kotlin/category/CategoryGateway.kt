package category

import pagination.Pagination
import java.util.Optional

interface CategoryGateway {

    fun create(category: Category):Category

    fun update(category: Category): Category

    fun deleteById(id: String)

    fun findById(id: String): Optional<Category>

    fun findAll(query: CategorySearchQuery): Pagination<Category>
}