package unit.com.rviewer.skeletons.infrastructure.config

import com.rviewer.skeletons.domain.repository.ItemRepository
import com.rviewer.skeletons.domain.repository.SafeboxRepository
import com.rviewer.skeletons.domain.service.PasswordManager
import com.rviewer.skeletons.domain.service.SafeboxService
import com.rviewer.skeletons.infrastructure.config.BeanConfig
import spock.lang.Specification
import spock.lang.Subject

class BeanConfigUnitSpec extends Specification {

    @Subject
    private BeanConfig beanConfig

    void setup() {
        beanConfig = new BeanConfig()
    }

    def "A Safebox service impl should be passed as an interface"() {
        given: "A mocked service parameter"
        def safeboxRepository = Mock(SafeboxRepository)
        and: "Another mocked service parameter"
        def passwordManager = Mock(PasswordManager)

        when: "The constructor is executed"
        def safeboxService = beanConfig.safeboxService(safeboxRepository, passwordManager)

        then: "The object is indeed constructed"
        safeboxService != null
    }

    def "An Item service impl should be passed as an interface"() {
        given: "A mocked service parameter"
        def itemRepository = Mock(ItemRepository)
        def safeboxService = Mock(SafeboxService)

        when: "The constructor is executed"
        def itemService = beanConfig.itemService(itemRepository, safeboxService)

        then: "The object is indeed constructed"
        itemService != null
    }
}
