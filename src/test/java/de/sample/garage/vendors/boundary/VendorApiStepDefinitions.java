package de.sample.garage.vendors.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sample.garage.test.slices.VendorBoundaryTest;
import de.sample.garage.vendors.domain.Vendor;
import de.sample.garage.vendors.domain.VendorService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@VendorBoundaryTest
@CucumberContextConfiguration
public class VendorApiStepDefinitions {

    @Autowired
    VendorService service;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    final Collection<Vendor> givenVendors = new LinkedList<>();
    ResultActions response;
    final List<InternalVendorDto> responseVendors = new LinkedList<>();

    @Data
    private static class InternalVendorDto {

        // no readonly field
        private String shortName;
        private String name;
        private String city;

    }

    @Before
    public void setup() {
        lenient().when(service.findAll()).thenReturn(givenVendors.stream());
    }

    @Given("we have vendor {string} with name {string} and city {string}")
    public void givenVendor(String shortName, String name, String city) {
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
    public void fetchVendors() throws Exception {
        response = mockMvc.perform(get("/vendors"));
        if (response.andReturn().getResponse().getStatus() == 200) {
            var body = response.andReturn().getResponse().getContentAsString();
            assertThat(body).isNotNull();
            var dtos = objectMapper.readValue(body, InternalVendorDto[].class);
            responseVendors.addAll(Arrays.asList(dtos));
        }
    }

    @Then("we get a result of size {int}")
    public void assertResponseBodyArrayOfSize(int size) throws Exception {
        response.andExpect(status().isOk());
        assertThat(responseVendors).hasSize(size);
    }

    @Then("the result contains a vendor {string} with name {string} and city {string}")
    public void assertResponseBodyArrayContainsVendor(String shortName, String name, String city) throws Exception {
        response.andExpect(status().isOk());
        InternalVendorDto expected = new InternalVendorDto();
        expected.setShortName(shortName);
        expected.setName(name);
        expected.setCity(city);
        assertThat(responseVendors).contains(expected);
    }

    @When("we save a vendor {string} without any information")
    public void saveEmptyVendor(String shortName) throws Exception {
        response = mockMvc.perform(
            put("/api/v1/vendors/" + shortName)
            //.contentType(MediaType.APPLICATION_JSON)
            //.content("{}")
        );
    }

    @Then("we get a validation error")
    public void assertValidationError() throws Exception {
        response.andExpect(status().is4xxClientError());
        verify(service, never()).save(any());
    }

}
