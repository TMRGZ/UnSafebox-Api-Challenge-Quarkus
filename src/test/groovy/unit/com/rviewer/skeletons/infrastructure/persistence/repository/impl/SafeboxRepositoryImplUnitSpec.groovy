package unit.com.rviewer.skeletons.infrastructure.persistence.repository.impl


import com.rviewer.skeletons.domain.model.Safebox
import com.rviewer.skeletons.infrastructure.mapper.SafeboxMapper
import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveSafeboxRepository
import com.rviewer.skeletons.infrastructure.persistence.repository.impl.SafeboxRepositoryImpl
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

class SafeboxRepositoryImplUnitSpec extends Specification {

    @Subject
    private SafeboxRepositoryImpl safeboxRepository

    private ReactiveSafeboxRepository reactiveSafeboxRepository = Mock(ReactiveSafeboxRepository)

    private SafeboxMapper safeboxMapper = Mock(SafeboxMapper)

    void setup() {
        safeboxRepository = new SafeboxRepositoryImpl(
                reactiveSafeboxRepository: reactiveSafeboxRepository,
                safeboxMapper: safeboxMapper
        )
    }

    def "Providing an id should find the safebox and map it"() {
        given: "An existing id"
        def id = 1
        and: "An existing safebox"
        def safebox = Safebox.builder().id(id).build()

        when: "The find is executed"
        safeboxRepository.findById(id)

        then: "A reactive element should be received"
        1 * reactiveSafeboxRepository.findById(id) >> Mono.just(safebox)
    }

    def "Providing a name should find the safebox and map it"() {
        given: "An existing name"
        def name = "TEST"
        and: "An existing safebox"
        def safebox = Safebox.builder().name(name).build()

        when: "The find is executed"
        safeboxRepository.findByNameIgnoreCase(name)

        then: "A reactive element should be received"
        1 * reactiveSafeboxRepository.findByNameIgnoreCase(name) >> Mono.just(safebox)
    }

    def "Providing a safebox should store it in DB after map it and then map it again"() {
        given: "A safebox to store"
        def safebox = Safebox.builder().build()

        when: "The save is executed"
        safeboxRepository.save(safebox)

        then: "The safebox should be mapped to a DAO"
        1 * safeboxMapper.map(safebox) >> SafeboxDao.builder().build()
        and: "The reactive save should be executed"
        1 * reactiveSafeboxRepository.save(_) >> Mono.just(SafeboxDao.builder().build())
    }
}
