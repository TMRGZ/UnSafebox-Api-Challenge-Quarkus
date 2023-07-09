package unit.com.rviewer.skeletons.domain.service.impl

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException
import com.rviewer.skeletons.domain.model.Item
import com.rviewer.skeletons.domain.model.Safebox
import com.rviewer.skeletons.domain.repository.ItemRepository
import com.rviewer.skeletons.domain.service.SafeboxService
import com.rviewer.skeletons.domain.service.impl.ItemServiceImpl
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

class ItemServiceImplUnitSpec extends Specification {

    @Subject
    private ItemServiceImpl itemService

    private ItemRepository itemRepository = Mock(ItemRepository)

    private SafeboxService safeboxService = Mock(SafeboxService)

    void setup() {
        itemService = new ItemServiceImpl(itemRepository, safeboxService)
    }

    def "When a safebox id is passed then it will try to find items related to it"() {
        given: "An existing safebox id"
        def safeboxId = 1

        when: "When passing it to the function"
        itemService.getItems(safeboxId).collectList().block()

        then: "It will check that the safebox exists"
        1 * safeboxService.getSafebox(safeboxId) >> Mono.just(Safebox.builder().id(safeboxId).build())
        and: "It will try to find the related items"
        1 * itemRepository.findBySafeboxId(safeboxId) >> Flux.empty()
    }

    def "When a non existing safebox id is passed then it will throw an error"() {
        given: "An non existing safebox id"
        def safeboxId = 1

        when: "When passing it to the function"
        itemService.getItems(safeboxId).collectList().block()

        then: "The safebox does not exist"
        1 * safeboxService.getSafebox(safeboxId) >> Mono.empty()
        and: "Can't find related items"
        0 * itemRepository.findBySafeboxId(safeboxId)
        and: "An exception is thrown"
        thrown(SafeboxDoesNotExistException)
    }

    def "When a safebox id and an item are passed, then it will try to save those related items"() {
        given: "An existing safebox id"
        def safeboxId = 1
        and: "An item to store"
        def item = Item.builder().safeboxId(safeboxId).build()

        when: "Passing the id and the item to the function"
        itemService.save(safeboxId, item).block()

        then: "It will check that the safebox exists"
        1 * safeboxService.getSafebox(safeboxId) >> Mono.just(Safebox.builder().id(safeboxId).build())
        and: "The original item wont be store but a modified one with the established relation will"
        0 * itemRepository.save(item)
        1 * itemRepository.save(_ as Item) >> Mono.just(item.toBuilder().safeboxId(safeboxId).build())
    }
}
