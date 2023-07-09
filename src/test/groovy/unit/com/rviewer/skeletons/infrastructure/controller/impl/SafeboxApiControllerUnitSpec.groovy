package unit.com.rviewer.skeletons.infrastructure.controller.impl

import com.rviewer.skeletons.application.model.SafeboxItemDto
import com.rviewer.skeletons.application.model.SafeboxRequestDto
import com.rviewer.skeletons.application.service.ItemApplicationService
import com.rviewer.skeletons.application.service.SafeboxApplicationService
import com.rviewer.skeletons.infrastructure.controller.impl.SafeboxApiController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject


class SafeboxApiControllerUnitSpec extends Specification {

    @Subject
    private SafeboxApiController safeboxApiController

    private SafeboxApplicationService safeboxApplicationService = Mock(SafeboxApplicationService)

    private ItemApplicationService itemApplicationService = Mock(ItemApplicationService)

    void setup() {
        safeboxApiController = new SafeboxApiController(
                safeboxApplicationService: safeboxApplicationService,
                itemApplicationService: itemApplicationService
        )
    }

    def "A serialized create safebox request should execute the service method"() {
        given: "A reactive safebox request"
        def monoSafeboxRequest = Mono.just(new SafeboxRequestDto(null, null))
        and: "A mandatory server web exchange object"
        def mockedServerExchange = Mock(ServerWebExchange)

        when: "The method related to the endpoint is executed"
        safeboxApiController.createSafebox(monoSafeboxRequest, mockedServerExchange)

        then: "The application service is executed"
        1 * safeboxApplicationService.createSafebox(monoSafeboxRequest)
    }

    def "A serialized get safebox items request should execute the service method"() {
        given: "An ID is received"
        def safeboxId = 0
        and: "A mandatory server web exchange object"
        def mockedServerExchange = Mock(ServerWebExchange)

        when: "The method related to the endpoint is executed"
        safeboxApiController.getSafeboxItems(safeboxId, mockedServerExchange)

        then: "The application service is executed"
        1 * itemApplicationService.getSafeboxItems(safeboxId)
    }

    def "A serialized save safebox items request should execute the service method"() {
        given: "An ID is received"
        def safeboxId = 0
        and: "A reactive list of items"
        def fluxOfItems = Flux.just(new SafeboxItemDto())
        and: "A mandatory server web exchange object"
        def mockedServerExchange = Mock(ServerWebExchange)

        when: "The method related to the endpoint is executed"
        safeboxApiController.saveSafeboxItems(safeboxId, fluxOfItems, mockedServerExchange)

        then: "The application service is executed"
        1 * itemApplicationService.saveSafeboxItems(safeboxId, fluxOfItems)
    }
}
