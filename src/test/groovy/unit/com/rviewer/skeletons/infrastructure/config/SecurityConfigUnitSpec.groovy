package unit.com.rviewer.skeletons.infrastructure.config


import com.rviewer.skeletons.domain.service.SafeboxService
import com.rviewer.skeletons.infrastructure.config.SecurityConfig
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import spock.lang.Specification
import spock.lang.Subject

class SecurityConfigUnitSpec extends Specification {

    @Subject
    private SecurityConfig securityConfig

    void setup() {
        securityConfig = new SecurityConfig()
    }

    def "The method configures a ReactiveUserDetailsService"() {
        given: "Mocked parameter"
        def safeboxService = Mock(SafeboxService)

        when: "The constructor is executed"
        ReactiveUserDetailsService userDetailsService = securityConfig.userDetailsService(safeboxService)

        then: "The objects holds an existing lambda function"
        userDetailsService != null
    }
}
