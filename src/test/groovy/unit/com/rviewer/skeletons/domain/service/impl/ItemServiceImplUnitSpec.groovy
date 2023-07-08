package unit.com.rviewer.skeletons.domain.service.impl

import com.rviewer.skeletons.domain.model.Item
import com.rviewer.skeletons.domain.repository.ItemRepository
import com.rviewer.skeletons.domain.service.impl.ItemServiceImpl
import spock.lang.Specification
import spock.lang.Subject

class ItemServiceImplUnitSpec extends Specification {

    @Subject
    private ItemServiceImpl itemService

    private ItemRepository itemRepository = Mock(ItemRepository)

    void setup() {
        itemService = new ItemServiceImpl(itemRepository)
    }

    def "When a safebox id is passed then it will try to find items related to it"() {
        given: "An existing safebox id"
        def safeboxId = 1

        when: "When passing it to the function"
        itemService.getItems(safeboxId)

        then: "It will try to find the related items"
        1 * itemRepository.findBySafeboxId(safeboxId)
    }

    def "When a safebox id and an item are passed, then it will try to save those related items"() {
        given: "An existing safebox id"
        def safeboxId = 1
        and: "An item to store"
        def item = Item.builder().safeboxId(safeboxId).build()

        when: "Passing the id and the item to the function"
        itemService.save(safeboxId, item)

        then: "The original item wont be store but a modified one with the established relation will"
        0 * itemRepository.save(item)
        1 * itemRepository.save(_ as Item)
    }
}
