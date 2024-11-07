package edu.harvard.iq.dataverse_hub.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.harvard.iq.dataverse_hub.model.ScheduledJob;

@SpringBootTest
public class ScheduledJobServiceTests {

    @Autowired
    ScheduledJobService scheduledJobService;

    @Test
    public void testScheduledJobService() {

        ScheduledJob scheduledJob = new ScheduledJob();
        scheduledJob.setJobName("test_delete");
        scheduledJobService.save(scheduledJob);
        
        assertEquals(scheduledJobService.isDue("test_delete"), true);
        scheduledJobService.deletScheduledJob("test_delete");
        scheduledJobService.isDue(null);
        scheduledJobService.findAll();

    }

}
