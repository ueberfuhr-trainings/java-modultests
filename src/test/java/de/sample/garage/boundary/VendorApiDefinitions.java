package de.sample.garage.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sample.garage.domain.Vendor;
import de.sample.garage.domain.VendorService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@GarageApiTest
public class VendorApiDefinitions {

    @Autowired
    VendorService service;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    final Collection<Vendor> givenVendors = new LinkedList<>();
    ResultActions response;
    final List<VendorDto> responseVendors = new LinkedList<>();

    @BeforeEach
    void setup() {
        when(service.findAll()).thenReturn(givenVendors);
    }

    @Given("we have vendor {string} with name {string} and city {string}")
    void givenVendor(String shortName, String name, String city) {
        givenVendors.add(
          Vendor
            .builder()
            .shortName(shortName)
            .name(name)
            .city(city)
            .build()
        );
    }

    @When("we fetch all vendors")
    void fetchVendors() throws Exception {
        response = mockMvc.perform(get("/api/v1/vendors"));
        if (response.andReturn().getResponse().getStatus() == 200) {
            var body = response.andReturn().getResponse().getContentAsString();
            assertThat(body).isNotNull();
            var dtos = objectMapper.readValue(body, VendorDto[].class);
            responseVendors.addAll(Arrays.asList(dtos));
        }
    }

    @Then("we get a result of size {int}")
    void assertResponseBodyArrayOfSize(int size) throws Exception {
        response.andExpect(status().isOk());
        assertThat(responseVendors).hasSize(size);
    }

    @Then("the result contains a vendor {string} with name {string} and city {string}")
    void assertResponseBodyArrayContainsVendor(String shortName, String name, String city) throws Exception {
        response.andExpect(status().isOk());
        VendorDto expected = new VendorDto();
        expected.setShortName(shortName);
        expected.setName(name);
        expected.setCity(city);
        assertThat(responseVendors).contains(expected);
    }

    @When("we save a vendor {string} without any information")
    void saveEmptyVendor(String shortName) throws Exception {
        response = mockMvc.perform(
          put("/api/v1/vendors/" + shortName)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")
        );
    }

    @Then("we get a validation error")
    void assertValidationError() throws Exception {
        response.andExpect(status().isUnprocessableEntity());
    }

}
