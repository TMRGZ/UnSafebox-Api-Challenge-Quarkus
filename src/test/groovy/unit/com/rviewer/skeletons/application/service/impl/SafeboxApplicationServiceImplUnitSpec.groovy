package unit.com.rviewer.skeletons.application.service.impl

import com.rviewer.skeletons.application.model.SafeboxRequestDto
import com.rviewer.skeletons.application.service.impl.SafeboxApplicatonServiceImpl
import com.rviewer.skeletons.domain.model.Safebox
import com.rviewer.skeletons.domain.service.SafeboxService
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class SafeboxApplicationServiceImplUnitSpec extends Specification {

    @Subject
    private SafeboxApplicatonServiceImpl safeboxApplicatonService

    private SafeboxService safeboxService = Mock(SafeboxService)

    void setup() {
        safeboxApplicatonService = new SafeboxApplicatonServiceImpl(
                safeboxService: safeboxService
        )
    }

    def "Receiving a reactive serialized request should execute the domain function an transform it's value"() {
        given: "The serialized input request"
        def name = "TEST"
        def pass = "TEST"
        def safeboxRequestDto = new SafeboxRequestDto(name, pass)
        and: "And a mocked dependency"
        def generatedId = 1
        def createdSafebox = Safebox.builder().id(generatedId).build()
        safeboxService.createSafebox(name, pass) >> Mono.just(createdSafebox)

        when: "The method is executed"
        def response = safeboxApplicatonService.createSafebox(Mono.just(safeboxRequestDto))

        then: "The reactive transformations actually happened"
        StepVerifier.create(response)
                .expectNextMatches { responseEntity ->
                    responseEntity?.getStatusCode() == HttpStatus.CREATED
                    && responseEntity.getBody().getId() == generatedId
                }
                .expectComplete()
                .verify()
    }
}
