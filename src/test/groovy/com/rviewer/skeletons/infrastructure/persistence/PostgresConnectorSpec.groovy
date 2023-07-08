package com.rviewer.skeletons.infrastructure.persistence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class PostgresConnectorSpec extends Specification {

//    @Subject
//    @Autowired
//    private PostgresConnector postgresConnector
//
//    private JdbcTemplate jdbcTemplate
//
//    void setup() {
//        jdbcTemplate = Mock(JdbcTemplate)
//    }
//
//    def "getConnectionStatus_shouldReturn2"() {
//        given:
//        jdbcTemplate.queryForObject("SELECT 1+1", Integer) >> 2
//
//        when:
//        int status = postgresConnector.getConnectionStatus()
//
//        then:
//        status == 2
//    }
}
