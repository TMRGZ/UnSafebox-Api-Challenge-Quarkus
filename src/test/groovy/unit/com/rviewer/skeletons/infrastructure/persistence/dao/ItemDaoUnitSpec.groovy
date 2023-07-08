package unit.com.rviewer.skeletons.infrastructure.persistence.dao

import com.rviewer.skeletons.infrastructure.persistence.dao.ItemDao
import spock.lang.Specification

class ItemDaoUnitSpec extends Specification {

    def "Giving an object there parameters should be meaningful"() {
        given: "An itemDao builder"
        ItemDao.ItemDaoBuilder itemDaoBuilder = ItemDao.builder()

        when: "The object is fully built"
        def itemDao = itemDaoBuilder
                .id(1)
                .content("TEST")
                .safeboxId(1)
                .build()

        then: "The object exists"
        itemDao != null
        and: "Its parameters too"
        itemDao.id != null
        itemDao.content != null
        itemDao.safeboxId != null
    }
}
