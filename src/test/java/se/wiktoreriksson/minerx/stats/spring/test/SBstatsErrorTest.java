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
public class SBstatsErrorTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/userapi?uuid=randomstring").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("{\"uuid\":\"randomstring\",\"error\":\"Internal 404: Did'nt find userdata. Try to login to this minecraft server, logout and reload this page.\",\"stack\":\"plugins/Essentials/userdata/randomstring.yml (No such file or directory)\"}")));
        System.out.println("error query done! Got string \"{\"uuid\":\"randomstring\",\"error\":\"Internal 404: Did'nt find userdata. Try to login to this minecraft server, logout and reload this page.\",\"stack\":\"plugins/Essentials/userdata/randomstring.yml (No such file or directory)\"}\"");
    }
}