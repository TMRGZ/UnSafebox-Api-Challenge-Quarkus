package com.rviewer.skeletons.infrastructure.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
//@AutoConfigureMockMvc
class PingControllerSpec extends Specification {

//    @Autowired
//    private MockMvc mockMvc

//    def "getPing_success"() throws Exception {
//        expect:
//        mockMvc
//                .perform(get("/ping"))
//                .andExpect(status().isOk())
//    }
//
//    def "getPing_returnsPong"() throws Exception {
//        expect:
//        mockMvc
//                .perform(get("/ping"))
//                .andExpect(content().string(containsString("pong")))
//    }
}
