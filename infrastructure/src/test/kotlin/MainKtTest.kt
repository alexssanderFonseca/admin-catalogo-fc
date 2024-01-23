import br.com.alexsdm.admin.catalogo.domain.infrastructure.main
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun testMain() {
        assertNotNull(main())
    }
}