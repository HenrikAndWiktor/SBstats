package se.wiktoreriksson.minerx.stats.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SBstatsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/userapi?uuid=59e113d1-3b5f-4d64-b65b-555dafc69cd4").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("{\"uuid\":\"59e113d1-3b5f-4d64-b65b-555dafc69cd4\",\"name\":\"WGameYT\",\"xyz\":\"-5.2173694941719/80.0/-188.79740187909692\",\"money\":\"null\",\"yawpitch\":\"-74.714905/4.8242645\"}")));
    }
}