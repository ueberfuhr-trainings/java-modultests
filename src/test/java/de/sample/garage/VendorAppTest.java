package de.sample.garage;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sample.garage.boundary.vendors.VendorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// otherwise, it uses the file based
// -> this would crash, when the app or another test is already running in parallel
@AutoConfigureTestDatabase
public class VendorAppTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnVendorAfterSave() throws Exception {
        // Arrange
        VendorDto input = new VendorDto();
        input.setCity("test-city");
        input.setName("test-name");
        String inputAsJson = objectMapper.writeValueAsString(input);
        // Save vendor
        mvc.perform(
            put("/api/v1/vendors/test")
              .contentType(MediaType.APPLICATION_JSON)
              .content(inputAsJson)
          )
          .andExpect(status().isNoContent());
        // Request all vendors
        mvc.perform(
            get("/api/v1/vendors")
              .accept(MediaType.APPLICATION_JSON)
          )
          .andExpect(status().isOk())
          .andDo(result -> {
              String body = result.getResponse().getContentAsString();
              VendorDto[] vendors = objectMapper.readValue(body, VendorDto[].class);
              assertThat(vendors)
                .hasSize(1)
                .extracting(VendorDto::getName)
                .contains(input.getName());
          });
    }

}
