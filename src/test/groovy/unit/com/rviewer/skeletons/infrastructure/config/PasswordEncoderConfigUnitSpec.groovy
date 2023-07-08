package unit.com.rviewer.skeletons.infrastructure.config


import com.rviewer.skeletons.infrastructure.config.PasswordEncoderConfig
import spock.lang.Specification
import spock.lang.Subject

class PasswordEncoderConfigUnitSpec extends Specification {

    @Subject
    private PasswordEncoderConfig passwordEncoderConfig

    void setup() {
        passwordEncoderConfig = new PasswordEncoderConfig()
    }

    def "A Safebox service impl should be passed as an interface"() {
        when: "The constructor is executed"
        def passwordEncoder = passwordEncoderConfig.passwordEncoder()

        then: "The object is indeed constructed"
        passwordEncoder != null
    }

}
