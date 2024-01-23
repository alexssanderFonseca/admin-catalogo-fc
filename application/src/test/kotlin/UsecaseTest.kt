import br.com.alexsdm.admin.catalogo.domain.application.Usecase
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UsecaseTest {

    @Test
    fun createUsecase() {
        assertNotNull(Usecase())
        assertNotNull(Usecase().execute())
    }
}