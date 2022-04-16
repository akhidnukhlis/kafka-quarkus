package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class consumer {

    @Incoming("text-message")
    public void sendMessage(String text) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        Map map = om.readValue(text, Map.class);

        Map<String, String> payload = new HashMap<>();
        payload.put("phone", map.get("phone").toString());
        payload.put("text", map.get("text").toString());

        System.out.println("Response: " + payload);
    }

}
