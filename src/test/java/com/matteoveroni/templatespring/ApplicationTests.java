package com.matteoveroni.templatespring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

    @Disabled
    @Nested
    @ActiveProfiles("dev")
    class DevProfileTests {

        @Autowired
        private Environment environment;

        @Test
        void testDevProfile() {
            String[] activeProfiles = environment.getActiveProfiles();
            assertThat(activeProfiles).containsExactly("dev");
        }
    }

    @Disabled
    @Nested
    @ActiveProfiles("prod")
    class ProdProfileTests {

        @Autowired
        private Environment environment;

        @Test
        void testProdProfile() {
            String[] activeProfiles = environment.getActiveProfiles();
            assertThat(activeProfiles).containsExactly("prod");
        }
    }

}
