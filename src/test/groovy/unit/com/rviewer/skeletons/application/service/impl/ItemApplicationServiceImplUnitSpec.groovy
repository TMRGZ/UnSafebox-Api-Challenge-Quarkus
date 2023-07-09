package unit.com.rviewer.skeletons.application.service.impl

import com.rviewer.skeletons.application.mapper.ItemDtoMapper
import com.rviewer.skeletons.application.model.SafeboxItemDto
import com.rviewer.skeletons.application.service.impl.ItemApplicationServiceImpl
import com.rviewer.skeletons.domain.model.Item
import com.rviewer.skeletons.domain.service.ItemService
import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao
import org.springframework.http.HttpStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class ItemApplicationServiceImplUnitSpec extends Specification {

    @Subject
    private ItemApplicationServiceImpl itemApplicationService

    private ItemService itemService = Mock(ItemService)

    private ItemDtoMapper itemDtoMapper = Mock(ItemDtoMapper)

    void setup() {
        itemApplicationService = new ItemApplicationServiceImpl(
                itemService: itemService,
                itemDtoMapper: itemDtoMapper
        )
    }

    def "Giving an existing safebox id, results in a reactive list of items"() {
        given: "An existing safebox id"
        def safeboxId = 0
        and: "A related item"
        def item = Item.builder().safeboxId(safeboxId).build()
        and: "A list of related items to that id"
        def items = [item]
        and: "A mocked response"
        def safeboxItemDto = new SafeboxItemDto()
        itemDtoMapper.map(item) >> safeboxItemDto

        when: "The parameters are received"
        def response = itemApplicationService.getSafeboxItems(safeboxId)

        then: "There are interactions with the dependencies"
        1 * itemService.getItems(safeboxId) >> Flux.fromIterable(items)
        and: "An expected response is received"
        StepVerifier.create(response)
                .expectNextMatches { responseEntity ->
                    responseEntity.statusCode == HttpStatus.OK && responseEntity.body
                            && StepVerifier.create(responseEntity.body)
                            .expectNext(safeboxItemDto)
                            .verifyComplete()
                }
                .expectComplete()
                .verify()
    }

    def "Giving an existing safebox id, the items get saved and related to it"() {
        given: "An existing safebox id"
        Long safeboxId = 0
        and: "A related item"
        def item = Item.builder().safeboxId(safeboxId).build()
        and: "A list of items"
        def itemDto = new SafeboxItemDto()
        def itemDtoList = [itemDto]
        and: "A mocked mapper result"
        itemDtoMapper.map(itemDto) >> item
        and: "A mocked saving result"
        itemService.save(safeboxId, item) >> Mono.just(SafeboxDao.builder().build())

        when: "The function is executed"
        def response = itemApplicationService.saveSafeboxItems(safeboxId, Flux.fromIterable(itemDtoList))

        then: "The saving process and it's transformation happened"
        StepVerifier.create(response)
                .expectNextMatches { responseEntity ->
                    responseEntity.statusCode == HttpStatus.CREATED && !responseEntity.body
                }
                .expectComplete()
                .verify()
    }
}
