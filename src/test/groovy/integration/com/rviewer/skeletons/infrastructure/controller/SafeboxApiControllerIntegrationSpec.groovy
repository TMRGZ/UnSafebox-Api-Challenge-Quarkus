package integration.com.rviewer.skeletons.infrastructure.controller

import com.rviewer.skeletons.RviewerSkeletonApplication
import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto
import com.rviewer.skeletons.application.model.SafeboxRequestDto
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveSafeboxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest(classes = RviewerSkeletonApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class SafeboxApiControllerIntegrationSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @Autowired
    private ReactiveSafeboxRepository reactiveSafeboxRepository

    private static final SAFEBOX_ENDPOINT = "/safebox"

    def "Calling the create safebox endpoint results in a 201"() {
        given: "A well formed request body"
        def name = "TEST"
        def password = "Test#12345"
        def safeboxRequest = new SafeboxRequestDto(name, password)

        when: "The endpoint is called"
        def response = webTestClient.post().uri(SAFEBOX_ENDPOINT)
                .bodyValue(safeboxRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreatedSafeboxResponseDto)
                .returnResult().responseBody

        then: "Check that the response is good and the DB has been affected"
        response.id
        reactiveSafeboxRepository.findById(response.id).block()

    }
}
