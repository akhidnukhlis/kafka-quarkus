package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class producer {

    @Inject
    @Channel("text-message")
    Emitter<String> emitter;

    @Path("/confirm")
    @GET
    public Response confirmMessage(@QueryParam("phoneNumber") String phoneNumber) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        Map<String, String> payload = new HashMap<>();
        payload.put("phone", phoneNumber);
        payload.put("text", "your message here");
        emitter.send(om.writeValueAsString(payload));

        return Response.ok(payload).build();
    }

}
