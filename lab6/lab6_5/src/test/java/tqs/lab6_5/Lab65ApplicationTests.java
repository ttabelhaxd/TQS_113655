package tqs.lab6_5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class Lab65ApplicationTests {

    @Test
    void contextLoads() {
    }

}
