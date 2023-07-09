package unit.com.rviewer.skeletons.infrastructure.persistence.repository.impl

import com.rviewer.skeletons.domain.model.Item
import com.rviewer.skeletons.infrastructure.mapper.ItemMapper
import com.rviewer.skeletons.infrastructure.persistence.dao.ItemDao
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveItemRepository
import com.rviewer.skeletons.infrastructure.persistence.repository.impl.ItemRepositoryImpl
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

class ItemRepositoryImplUnitSpec extends Specification {

    @Subject
    private ItemRepositoryImpl itemRepository

    private ReactiveItemRepository reactiveItemRepository = Mock(ReactiveItemRepository)

    private ItemMapper itemMapper = Mock(ItemMapper)

    void setup() {
        itemRepository = new ItemRepositoryImpl(
                reactiveItemRepository: reactiveItemRepository,
                itemMapper: itemMapper
        )
    }

    def "Providing an id should find items related and map them"() {
        given: "An existing id"
        def safeboxId = 0
        and: "A list of items"
        def itemList = [Item.builder().safeboxId(safeboxId).build()]

        when: "The find is executed"
        itemRepository.findBySafeboxId(safeboxId)

        then: "A reactive list of elements should be received"
        1 * reactiveItemRepository.findBySafeboxId(safeboxId) >> Flux.fromIterable(itemList)
    }

    def "Providing an item should store them in DB after map them and then map them again"() {
        given: "An item to store"
        def item = Item.builder().build()

        when: "The save is executed"
        itemRepository.save(item)

        then: "The item should be mapped to a DAO"
        1 * itemMapper.map(item) >> ItemDao.builder().build()
        and: "The reactive save should be executed"
        1 * reactiveItemRepository.save(_) >> Mono.just(ItemDao.builder().build())
    }
}
