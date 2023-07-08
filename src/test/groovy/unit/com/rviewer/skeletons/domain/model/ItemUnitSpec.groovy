package unit.com.rviewer.skeletons.domain.model

import com.rviewer.skeletons.domain.model.Item
import spock.lang.Specification

class ItemUnitSpec extends Specification {

    def "Giving an object there parameters should be meaningful"() {
        given: "An item builder"
        Item.ItemBuilder itemBuilder = Item.builder()

        when: "The object is fully built"
        def item = itemBuilder
                .content("TEST")
                .safeboxId(1)
                .build()

        then: "The object exists"
        item != null
        and: "Its parameters too"
        item.content != null
        item.safeboxId != null
    }
}
