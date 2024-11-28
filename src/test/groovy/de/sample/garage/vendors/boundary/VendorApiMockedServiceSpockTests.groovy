package de.sample.garage.vendors.boundary

import com.fasterxml.jackson.databind.ObjectMapper
import de.sample.garage.test.slices.VendorBoundarySpockTest
import de.sample.garage.vendors.domain.Vendor
import de.sample.garage.vendors.domain.VendorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

// https://github.com/spockframework/spock/issues/1539
@VendorBoundarySpockTest
class VendorApiMockedServiceSpockTests extends Specification {

    @Autowired
    // Mock
    VendorService service
    @Autowired
    MockMvc mvc
    @Autowired
    ObjectMapper objectMapper

    def "should return vendors in snake case"() {
        given:
        def sampleVendor = Vendor.builder() shortName "TEST" name "name" city "city" build()
        def request = get "/vendors"
        when:
        def response = mvc.perform request andReturn() response
        then:
        1 * service.findAll() >> [sampleVendor].stream()
        response.status == 200
    }

    def "should return NO_CONTENT on put"() {
        given:
        def input = new VendorDto()
        input.city = "test-city"
        input.name = "test-name"
        def inputAsJson = objectMapper.writeValueAsString input
        def request = put "/vendors/test"                 \
                           contentType MediaType.APPLICATION_JSON content inputAsJson
        when:
        def response = mvc.perform request andReturn() response
        then:
        1 * service.save(_)
        response.status == 204
    }

    def "should save short name from path"() {
        given:
        def input = new VendorDto()
        input.shortName = "SHOULD-BE-IGNORED"
        input.city = "test-city"
        input.name = "test-name"
        def inputAsJson = objectMapper.writeValueAsString input
        def shortName = "shorty"
        def request = put "/vendors/" + shortName                 \
                           contentType MediaType.APPLICATION_JSON content inputAsJson
        when:
        def response = mvc.perform request andReturn() response
        then:
        1 * service.save({
            it.shortName == shortName
        })
        response.status == 204
    }

}
