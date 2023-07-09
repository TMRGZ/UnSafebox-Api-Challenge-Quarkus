package integration.com.rviewer.skeletons.infrastructure.controller

import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto
import com.rviewer.skeletons.application.model.SafeboxItemDto
import com.rviewer.skeletons.application.model.SafeboxRequestDto
import com.rviewer.skeletons.domain.model.Item
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveItemRepository
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveSafeboxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class SafeboxApiControllerIntegrationSpec extends AbstractControllerIntegrationSpec {

    @Autowired
    private ReactiveSafeboxRepository reactiveSafeboxRepository

    @Autowired
    private ReactiveItemRepository reactiveItemRepository

    private static final SAFEBOX_ENDPOINT = "/safebox"
    private static final SAFEBOX_ITEMS_ENDPOINT = SAFEBOX_ENDPOINT + "/{id}/items"

    def "Calling the create safebox endpoint results in a 201"() {
        given: "A well formed request body"
        def name = "TEST"
        def password = "Test#12345"
        def safeboxRequest = new SafeboxRequestDto(name, password)

        when: "The endpoint is called"
        def response = webTestClient.post().uri(SAFEBOX_ENDPOINT)
                .bodyValue(safeboxRequest)
                .exchange()

        then: "Check that the response is good"
        def body = response.expectStatus().isCreated()
                .expectBody(CreatedSafeboxResponseDto)
                .returnResult().responseBody
        and: "The DB has been affected"
        reactiveSafeboxRepository.findById(body.id).block()
    }

    def "Calling the create safebox endpoint with a weak password results in a 400"() {
        given: "A request body with a weak password"
        def name = "TEST"
        def password = "bad"
        def safeboxRequest = new SafeboxRequestDto(name, password)

        when: "The endpoint is called"
        def response = webTestClient.post().uri(SAFEBOX_ENDPOINT)
                .bodyValue(safeboxRequest)
                .exchange()

        then: "Check that the response is expected"
        response.expectStatus().isBadRequest()
    }

    def "Calling the create safebox endpoint trying to create an existing safebox results in a 409"() {
        given: "A request body with an already existing name"
        def name = "EXISTING_SAFEBOX"
        def password = "Test#12345"
        def safeboxRequest = new SafeboxRequestDto(name, password)

        when: "The endpoint is called"
        def response = webTestClient.post().uri(SAFEBOX_ENDPOINT)
                .bodyValue(safeboxRequest)
                .exchange()

        then: "Check that the response is expected"
        response.expectStatus().isEqualTo(HttpStatus.CONFLICT)
    }

    def "Calling the get safebox items endpoint results in a 200 receiving the data"() {
        given: "An existing safebox id"
        def safeboxId = 1000001
        and: "An existing safebox to login"
        def name = "EXISTING_SAFEBOX"
        def password = "EXISTING_SAFEBOX"

        when: "The endpoint is called"
        def response = webTestClient.get().uri(SAFEBOX_ITEMS_ENDPOINT, safeboxId)
                .headers(headers -> headers.setBasicAuth(name, password))
                .exchange()

        then: "Check that the response is good"
        response.expectStatus().isOk()
                .expectBodyList(SafeboxItemDto)
                .returnResult().responseBody
    }

    def "Calling the get safebox items endpoint with an non existing safebox id results in a 404"() {
        given: "A non existing safebox id"
        def safeboxId = 0
        and: "An existing safebox to login"
        def name = "EXISTING_SAFEBOX"
        def password = "EXISTING_SAFEBOX"

        when: "The endpoint is called"
        def response = webTestClient.get().uri(SAFEBOX_ITEMS_ENDPOINT, safeboxId)
                .headers(headers -> headers.setBasicAuth(name, password))
                .exchange()

        then: "Check that the response is good"
        response.expectStatus().isNotFound()
    }

    def "Calling the put safebox items endpoint results in a 201 saving the data"() {
        given: "An existing safebox id"
        def safeboxId = 1000001
        and: "An existing safebox to login"
        def name = "EXISTING_SAFEBOX"
        def password = "EXISTING_SAFEBOX"
        and: "A well formed request body"
        def itemContent = "NEW_DATA"
        def itemToStore = Item.builder().content(itemContent).build()

        when: "The endpoint is called"
        def response = webTestClient.put().uri(SAFEBOX_ITEMS_ENDPOINT, safeboxId)
                .headers(headers -> headers.setBasicAuth(name, password))
                .bodyValue(itemToStore)
                .exchange()

        then: "Check that the response is good"
        response.expectStatus().isCreated()
        and: "That there is a new item related to the safebox"
        def relatedItems = reactiveItemRepository.findBySafeboxId(safeboxId).collectList().block()
        relatedItems.stream().filter { it.content == itemContent }.findAny().isPresent()
    }

    def "Calling the put safebox items with an non existing safebox id results in a 404"() {
        given: "A non existing safebox id"
        def safeboxId = 0
        and: "An existing safebox to login"
        def name = "EXISTING_SAFEBOX"
        def password = "EXISTING_SAFEBOX"
        and: "A well formed request body"
        def itemContent = "NEW_DATA"
        def itemToStore = Item.builder().content(itemContent).build()

        when: "The endpoint is called"
        def response = webTestClient.put().uri(SAFEBOX_ITEMS_ENDPOINT, safeboxId)
                .headers(headers -> headers.setBasicAuth(name, password))
                .bodyValue(itemToStore)
                .exchange()

        then: "Check that the response is good"
        response.expectStatus().isNotFound()
    }
}
