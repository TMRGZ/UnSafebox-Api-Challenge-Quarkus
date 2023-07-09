package unit.com.rviewer.skeletons.domain.model


import com.rviewer.skeletons.domain.model.Safebox
import spock.lang.Specification

class SafeboxUnitSpec extends Specification {

    def "Giving an object there parameters should be meaningful"() {
        given: "An safebox builder"
        Safebox.SafeboxBuilder safeboxBuilder = Safebox.builder()

        when: "The object is fully built"
        def safebox = safeboxBuilder
                .id(1)
                .name("TEST")
                .password("TEST")
                .build()

        then: "The object exists"
        safebox != null
        and: "Its parameters too"
        safebox.id != null
        safebox.name != null
        safebox.password != null
    }
}
