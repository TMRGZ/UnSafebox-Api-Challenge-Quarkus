package unit.com.rviewer.skeletons.infrastructure.persistence.dao

import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao
import spock.lang.Specification

class SafeboxDaoUnitSpec extends Specification {

    def "Giving an object there parameters should be meaningful"() {
        given: "An safeboxDao builder"
        SafeboxDao.SafeboxDaoBuilder safeboxDaoBuilder = SafeboxDao.builder()

        when: "The object is fully built"
        def safeboxDao = safeboxDaoBuilder
                .id(1)
                .name("TEST")
                .password("TEST")
                .build()

        then: "The object exists"
        safeboxDao != null
        and: "Its parameters too"
        safeboxDao.id != null
        safeboxDao.name != null
        safeboxDao.password != null
    }

}
