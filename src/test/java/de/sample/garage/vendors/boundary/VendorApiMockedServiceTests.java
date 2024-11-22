package de.sample.garage.vendors.boundary;

import de.sample.garage.vendors.domain.Vendor;
import de.sample.garage.vendors.domain.VendorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// otherwise, it uses the file based
// -> this would crash, when the app or another test is already running in parallel
@AutoConfigureTestDatabase
class VendorApiMockedServiceTests {

    @MockitoBean
    VendorService service;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturn404IfNoVendorFound() throws Exception {
        String shortName = "test";
        when(service.findByShortName(shortName)).thenReturn(Optional.empty());
        mvc.perform(
                get("/vendors" + shortName)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnVendorsInSnakeCase() throws Exception {
        when(service.findAll()).thenReturn(
            Stream.of(
                Vendor.builder()
                    .shortName("TEST")
                    .name("name")
                    .city("city")
                    .build()
            )
        );
        mvc.perform(get("/vendors"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("*.short_name").value("TEST"));
    }

    @Test
    void shouldReturnNoContentOnPut() throws Exception {
        mvc.perform(
                put("/vendors/test")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                          {
                            "city": "test-city",
                            "name": "test-name"
                          }
                        """)
            )
            .andExpect(status().isNoContent());
        verify(service).save(any());
    }

    @Test
    void shouldSaveShortNameFromPath() throws Exception {
        String shortName = "shorty";
        mvc.perform(
                put("/vendors/" + shortName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                          {
                            "city": "test-city",
                            "name": "test-name",
                            "short_name": "SHOULD-BE-IGNORED"
                          }
                        """)
            )
            .andExpect(status().isNoContent());
        var captor = ArgumentCaptor.forClass(Vendor.class);
        verify(service, atLeast(0)).save(captor.capture());
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
