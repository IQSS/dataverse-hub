package edu.harvard.iq.dataverse_hub.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ServerMessageResponseTests {

    @Test
    public void testServerMessgaeResponse(){
        ServerMessageResponse serverMessageResponse 
            = new ServerMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, new Exception("Test"));

        assertNotNull(serverMessageResponse.toString());
        
        Date d = new Date();
        serverMessageResponse.setTimestamp(d);
        serverMessageResponse.setCode(0);
        serverMessageResponse.setStatus("Status");
        serverMessageResponse.setStackTrace("Trace");
        serverMessageResponse.setMessage("Message");

        assertEquals(serverMessageResponse.getTimestamp(), d);
        assertEquals(serverMessageResponse.getCode(), 0);
        assertEquals(serverMessageResponse.getStatus(), "Status");
        assertEquals(serverMessageResponse.getStackTrace(), "Trace");
        assertEquals(serverMessageResponse.getMessage(), "Message");
        
    }

}
