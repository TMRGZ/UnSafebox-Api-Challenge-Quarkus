package unit.com.rviewer.skeletons.domain.service.impl

import com.rviewer.skeletons.domain.model.Safebox
import com.rviewer.skeletons.domain.repository.SafeboxRepository
import com.rviewer.skeletons.domain.service.PasswordManager
import com.rviewer.skeletons.domain.service.impl.SafeboxServiceImpl
import spock.lang.Specification
import spock.lang.Subject

class SafeboxServiceImplUnitSpec extends Specification {

    @Subject
    private SafeboxServiceImpl safeboxService

    private SafeboxRepository safeboxRepository = Mock(SafeboxRepository)

    private PasswordManager passwordManager = Mock(PasswordManager)

    void setup() {
        safeboxService = new SafeboxServiceImpl(safeboxRepository, passwordManager)
    }

    def "Giving a name and a password a new safebox should be stored"() {
        given: "A name"
        def name = "TEST"
        and: "A password"
        def pass = "TEST"

        when: "The parameters are sent to the function"
        safeboxService.createSafebox(name, pass)

        then: "The password should be encoded"
        1 * passwordManager.encode(pass) >> pass
        and: "The safebox persisted"
        1 * safeboxRepository.save(_ as Safebox)
    }

    def "Giving a name a safebox should be found"() {
        given: "A name"
        def name = "TEST"

        when: "The name is given"
        safeboxService.getSafebox(name)

        then: "A safebox find function should be executed"
        1 * safeboxRepository.findByNameIgnoreCase(name)
    }
}
