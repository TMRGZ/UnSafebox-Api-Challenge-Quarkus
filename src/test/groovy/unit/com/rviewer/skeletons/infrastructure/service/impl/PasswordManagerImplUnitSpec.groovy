package unit.com.rviewer.skeletons.infrastructure.service.impl


import com.rviewer.skeletons.infrastructure.service.impl.PasswordManagerImpl
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

class PasswordManagerImplUnitSpec extends Specification {

    @Subject
    private PasswordManagerImpl passwordManager

    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)

    void setup() {
        passwordManager = new PasswordManagerImpl(passwordEncoder: passwordEncoder)
    }

    def "Passing an string should result in an encoded string"() {
        given: "An string"
        def password = "TEST"

        when: "The method is executed"
        passwordManager.encode(password)

        then: "The string should be encoded"
        1 * passwordEncoder.encode(password)
    }

    def "Passing a plain string and an encoded one should be checked"() {
        given: "A decoded string"
        def plainPassword = "TEST"
        and: "An encoded string"
        def encodedPassword = "ENCODED-TEST"

        when: "The method is executed"
        passwordManager.matches(plainPassword, encodedPassword)

        then: "Both string should be checked out"
        1 * passwordEncoder.matches(plainPassword, encodedPassword)
    }
}
