package integration.com.rviewer.skeletons.infrastructure.controller

import com.rviewer.skeletons.RviewerSkeletonApplication
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest(classes = RviewerSkeletonApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AbstractControllerIntegrationSpec extends Specification {

    @Autowired
    WebTestClient webTestClient

    @SpringBean
    PasswordEncoder passwordEncoder = Mock(PasswordEncoder)

    private void setup() {
        passwordEncoder.encode(_ as String) >> (String raw) -> raw
        passwordEncoder.matches(_ as String, _ as String) >> (String raw, String encoded) -> raw == encoded
    }
}
