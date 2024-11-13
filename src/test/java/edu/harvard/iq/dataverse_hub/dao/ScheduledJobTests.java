package edu.harvard.iq.dataverse_hub.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.harvard.iq.dataverse_hub.model.ScheduledJob;


public class ScheduledJobTests {

    @Test
    public void testScheduledJob() {

         assertDoesNotThrow(() -> {

            ScheduledJob job = new ScheduledJob();
            job.setJobId(1);
            job.setDescription("description");
            job.setJobName("jobName");
            job.setFrequency(1);

            assertEquals(job.getJobId(), 1);
            assertEquals(job.getDescription(), "description");
            assertEquals(job.getJobName(), "jobName");
            assertEquals(job.getFrequency(), 1);
            assertNotNull(job.toString());
           
        });
    
    }

}
