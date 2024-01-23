package category

import exception.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import validation.handler.ThrowsValidationHandler
import kotlin.test.*

class CategoryTest {

    @Test
    fun `Given valid parameters when category is create then instantiate a category`() {
        val expectedName = "Comedia"
        val expectedDescription = "Best movies"
        val expectedIsActive = true;

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNull(actualCategory.deletedAt)
    }

    @Test
    fun `Given an category name with less than 3 characters when new category is created should return an error`() {
        val expectedName = "Aç"
        val expectedErrorCount = 1
        val expectedErrorMessage = "'name' must be between 3 and 255 characters"
        val expectedDescription = ""
        val expectedIsActive = true

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        val actualException = assertThrows<DomainException> {
            val handler = ThrowsValidationHandler()
            actualCategory.validate(handler)
        }
        val messageException = actualException.message

        assertEquals(expectedErrorCount, actualException.errorDomains.size)
        assertEquals(expectedErrorMessage, messageException)
    }

    @Test
    fun `Given an blank category name when new category is created should return an error`() {
        val expectedName = "  "
        val expectedErrorCount = 1
        val expectedErrorMessage = "'name' should not be empty"
        val expectedDescription = ""
        val expectedIsActive = true

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        val actualException = assertThrows<DomainException> {
            val handler = ThrowsValidationHandler()
            actualCategory.validate(handler)
        }
        val messageException = actualException.message

        assertEquals(expectedErrorCount, actualException.errorDomains.size)
        assertEquals(expectedErrorMessage, messageException)
    }

    @Test
    fun `Given an blank description when new category is created should instantiate`() {
        val expectedName = "  "
        val expectedDescription = ""
        val expectedIsActive = true

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )


        val handler = ThrowsValidationHandler()
        actualCategory.validate(handler)

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNull(actualCategory.deletedAt)
    }

    @Test
    fun `Given an no active category when new category is created should instantiate`() {
        val expectedName = "Aventura"
        val expectedDescription = ""
        val expectedIsActive = false

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )


        val handler = ThrowsValidationHandler()
        assertDoesNotThrow { actualCategory.validate(handler) }

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNotNull(actualCategory.deletedAt)
    }

    @Test
    fun `Given a valid category inactive when category is actived then should active`() {
        val expectedName = "Comedia"
        val expectedDescription = "Best movies"
        val expectedIsActive = false

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        val handler = ThrowsValidationHandler()
        assertDoesNotThrow { actualCategory.validate(handler) }

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNotNull(actualCategory.deletedAt)

        val actualCategoryUpdateAt = actualCategory.updatedAt
        actualCategory.active()

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(true, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertTrue { actualCategoryUpdateAt.isBefore(actualCategory.updatedAt) }
        assertTrue { actualCategory.isActive }
        assertNull(actualCategory.deletedAt)
    }

    @Test
    fun `Given a valid category active when category is disabled then should disable`() {
        val expectedName = "Comedia"
        val expectedDescription = "Best movies"
        val expectedIsActive = true

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        val handler = ThrowsValidationHandler()
        assertDoesNotThrow { actualCategory.validate(handler) }

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNull(actualCategory.deletedAt)

        val actualCategoryUpdateAt = actualCategory.updatedAt
        actualCategory.disable()

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(false, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertTrue { actualCategoryUpdateAt.isBefore(actualCategory.updatedAt) }
        assertFalse { actualCategory.isActive }
        assertNotNull(actualCategory.deletedAt)
    }

    @Test
    fun `Given a valid category when category is updated then should update`() {
        val expectedName = "Comedia"
        val expectedDescription = "Best movies"
        val expectedIsActive = true

        val actualCategory = Category(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        val updatedName = "Comedy"
        val updatedDescription = "For fun"
        val updatedIsActive = false

        val handler = ThrowsValidationHandler()
        assertDoesNotThrow { actualCategory.validate(handler) }

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(expectedName, actualCategory.name)
        assertEquals(expectedDescription, actualCategory.description)
        assertEquals(expectedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertNotNull(actualCategory.updatedAt)
        assertNull(actualCategory.deletedAt)

        val actualCategoryUpdateAt = actualCategory.updatedAt
        actualCategory.update(name = updatedName, description = updatedDescription, isActive = updatedIsActive)

        assertNotNull(actualCategory)
        assertNotNull(actualCategory.id)
        assertEquals(updatedName, actualCategory.name)
        assertEquals(updatedDescription, actualCategory.description)
        assertEquals(updatedIsActive, actualCategory.isActive)
        assertNotNull(actualCategory.createdAt)
        assertTrue { actualCategoryUpdateAt.isBefore(actualCategory.updatedAt) }
        assertFalse { actualCategory.isActive }
        assertNotNull(actualCategory.deletedAt)
    }


}