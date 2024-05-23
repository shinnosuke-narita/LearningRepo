import com.example.androidtestpractice.chapter2.InputChecker
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import  org.assertj.core.api.Assertions.*

class InputCheckerTest {
    lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        val actual = target.isValid("ab")
        assertThat(actual).isFalse
    }

    @Test
    fun isValid_givenAlphabetic_returnsTrue() {
        val actual = target.isValid("abc")
        assertTrue(actual)
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        val actual = target.isValid("123")
        assertTrue(actual)
    }

    @Test
    fun isValid_givenAlphabeticNumerid_ic_returnsTrue() {
        val actual = target.isValid("123abc")
        assertTrue(actual)
    }

    @Test
    fun isValid_givenInvalidCharacter_returnsFalse() {
        val actual = target.isValid("abe@123")
        assertFalse(actual)
    }


    @Test
    fun isValid_givenNull_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { target.isValid(null) }
            .withMessage("Cannot be null")
    }
}
