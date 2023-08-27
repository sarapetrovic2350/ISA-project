package ISA.BloodBank.tests;
import ISA.BloodBank.iservice.IMedicalCenterService;
import ISA.BloodBank.model.MedicalCenter;
import ISA.BloodBank.service.MedicalCenterService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RateLimiterTests {

    private final Logger LOG = LoggerFactory.getLogger(MedicalCenterService.class);
    private final int centerCount = 6;

    @Autowired
    private IMedicalCenterService medicalCenterService;

    @Test
    public void testRateLimiterExceptionScenario() throws InterruptedException {
        LOG.info("Prvi poziv metode anotirane sa @RateLimiter");
        List<MedicalCenter> medicalCenters = medicalCenterService.getAll();
        assertEquals(centerCount, medicalCenters.size());

        LOG.info("Poziv metode anotirane sa @RateLimiter U OKVIRU ogranicenog vremena od 7 sekundi jos 3 puta");
        assertThrows(RequestNotPermitted.class, this::findAllThreeTimes);

        Thread.sleep(7000); // da bi ogranicen interval od 7 sekundi prosao

        LOG.info("Poziv metode anotirane sa @RateLimiter NAKON ogranicenog vremena od 7 sekundi");
        medicalCenters = medicalCenterService.getAll();
        assertEquals(centerCount, medicalCenters.size());
    }

    private void findAllThreeTimes(){
        medicalCenterService.getAll();
        medicalCenterService.getAll();
        medicalCenterService.getAll();
    }
}

