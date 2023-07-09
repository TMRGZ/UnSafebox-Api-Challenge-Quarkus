package unit.com.rviewer.skeletons.domain.service.impl

import com.rviewer.skeletons.domain.exception.SafeboxAlreadyExistsException
import com.rviewer.skeletons.domain.model.Safebox
import com.rviewer.skeletons.domain.repository.SafeboxRepository
import com.rviewer.skeletons.domain.service.PasswordManager
import com.rviewer.skeletons.domain.service.impl.SafeboxServiceImpl
import reactor.core.publisher.Mono
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
        safeboxService.createSafebox(name, pass).block()

        then: "Checked that there is no safebox with the same name"
        1 * safeboxRepository.findByNameIgnoreCase(name) >> Mono.empty()
        and: "The password should be encoded"
        1 * passwordManager.encode(pass) >> pass
        and: "The safebox persisted"
        1 * safeboxRepository.save(_ as Safebox) >> Mono.just(Safebox.builder().build())
    }

    def "Giving a name already existing safebox name, should throw an error"() {
        given: "A name"
        def name = "TEST"
        and: "A password"
        def pass = "TEST"

        when: "The parameters are sent to the function"
        safeboxService.createSafebox(name, pass).block()

        then: "An existing safebox is found"
        1 * safeboxRepository.findByNameIgnoreCase(name) >> Mono.just(Safebox.builder().build())
        and: "The password shouldn't be encoded"
        0 * passwordManager.encode(pass)
        and: "The safebox is not persisted"
        0 * safeboxRepository.save(_ as Safebox)
        and: "An exception is thrown"
        thrown(SafeboxAlreadyExistsException)
    }

    def "Giving an id a safebox should be found"() {
        given: "An id"
        def id = 1

        when: "The id is given"
        safeboxService.getSafebox(id)

        then: "A safebox find function should be executed"
        1 * safeboxRepository.findById(id)
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
