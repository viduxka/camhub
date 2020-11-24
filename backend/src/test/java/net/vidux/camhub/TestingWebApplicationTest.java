package net.vidux.camhub;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Disabled
class TestingWebApplicationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void getCamerasEndPointTest() throws Exception {
    this.mockMvc
        .perform(get("/api/v1/cameras"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(
            content()
                //
                // .json("{\"_embedded\":{\"cameras\":[{\"name\":\"Alma\",\"ip\":\"123.123.123.123\",\"firmware\":\"1.0\",\"lastSeen\":\"2020-11-19T08:55:41.000+00:00\"},{\"name\":\"Barack\",\"ip\":\"123.123.123.124\",\"firmware\":\"1.0\",\"lastSeen\":\"2020-11-19T08:55:51.000+00:00\"},{\"name\":\"Citrom\",\"ip\":\"123.123.123.125\",\"firmware\":\"1.0\",\"lastSeen\":\"2020-11-19T08:56:01.000+00:00\"}]}}"));
                .json("{}"));
  }
}
