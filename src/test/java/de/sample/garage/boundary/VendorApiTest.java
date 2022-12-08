package de.sample.garage.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sample.garage.domain.Vendor;
import de.sample.garage.domain.VendorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@GarageApiTest
class VendorApiTest {

    @Autowired
    VendorService service;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnVendorsInSnakeCase() throws Exception {
        when(service.findAll()).thenReturn(
          List.of(
            Vendor.builder()
              .shortName("TEST")
              .name("name")
              .city("city")
              .build()
          )
        );
        mvc.perform(get("/api/v1/vendors"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("*.short_name").value("TEST"));
    }

    @Test
    void shouldReturnNoContentOnPut() throws Exception {
        VendorDto input = new VendorDto();
        input.setCity("test-city");
        input.setName("test-name");
        String inputAsJson = objectMapper.writeValueAsString(input);
        mvc.perform(
            put("/api/v1/vendors/test")
              .contentType(MediaType.APPLICATION_JSON)
              .content(inputAsJson)
          )
          .andExpect(status().isNoContent());
        verify(service).save(any());
    }

    @Test
    void shouldSaveShortNameFromPath() throws Exception {
        VendorDto input = new VendorDto();
        input.setShortName("SHOULD-BE-IGNORED");
        input.setCity("test-city");
        input.setName("test-name");
        String inputAsJson = objectMapper.writeValueAsString(input);
        String shortName = "shorty";
        mvc.perform(
            put("/api/v1/vendors/" + shortName)
              .contentType(MediaType.APPLICATION_JSON)
              .content(inputAsJson)
          )
          .andExpect(status().isNoContent());
        var captor = ArgumentCaptor.forClass(Vendor.class);
        verify(service).save(captor.capture());
        assertThat(captor.getValue())
          .extracting(Vendor::getShortName)
          .isEqualTo(shortName);
    }



    /*
     * optional (TDD):
     *  - PUT with empty name -> 422
     *  - PUT with xml -> 415
     */

}
