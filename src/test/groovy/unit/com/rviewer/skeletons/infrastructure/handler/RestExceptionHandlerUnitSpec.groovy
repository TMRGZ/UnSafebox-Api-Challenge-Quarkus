package unit.com.rviewer.skeletons.infrastructure.handler

import com.rviewer.skeletons.infrastructure.handler.RestExceptionHandler
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class RestExceptionHandlerUnitSpec extends Specification {

    @Subject
    private RestExceptionHandler restExceptionHandler

    void setup() {
        restExceptionHandler = new RestExceptionHandler()
    }

    def "A handler for a safebox that does not exist should return 404"() {
        when: "The handler is executed"
        def response = restExceptionHandler.safeboxNotFound()

        then: "Should return the response code"
        response.statusCode == HttpStatus.NOT_FOUND
    }

    def "A handler for a safebox that already exists should return 409"() {
        when: "The handler is executed"
        def response = restExceptionHandler.safeboxAlreadyExists()

        then: "Should return the response code"
        response.statusCode == HttpStatus.CONFLICT
    }
}
