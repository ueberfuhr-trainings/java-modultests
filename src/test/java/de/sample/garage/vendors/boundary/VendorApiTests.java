package de.sample.garage.vendors.boundary;

import de.sample.garage.test.slices.GarageApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@GarageApplicationTest
public class VendorApiTests {

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnVendorAfterSave() throws Exception {
        // Save vendor
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
        // Request all vendors
        mvc.perform(
                get("/vendors")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name").value("test-name"));
    }

}
