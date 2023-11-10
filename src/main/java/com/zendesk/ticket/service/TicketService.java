package com.zendesk.ticket.service;

import com.zendesk.ticket.config.CredConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {

    private CredConfig credConfig;
    private RestTemplate restTemplate;

    @Autowired
    public TicketService(CredConfig credConfig, RestTemplate restTemplate) {
        this.credConfig = credConfig;
        this.restTemplate = restTemplate;
    }
    public ResponseEntity<String> getData(){
        System.out.println("GetData!!!");
        try {
            String auth = credConfig.getApiUsername()+ ":" + credConfig.getApiPassword();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", authHeader);

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            return restTemplate.exchange(
                    "https://z3nzendeskcodingchallengehelp.zendesk.com/api/v2/tickets",
                    HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    public ResponseEntity<String> setData(Map<String, String> ticket){
        //RestTemplate restTemplate = new RestTemplate();
        System.out.println("Inside setData ");
        try {
            String auth = credConfig.getApiUsername() + ":" + credConfig.getApiPassword();

            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);

            // Just as in your Node.js code, Zendesk API wants tickets in a certain format:
            Map<String, Object> ticketData = new HashMap<>();
            ticketData.put("subject", ticket.get("subject"));
            ticketData.put("comment", Collections.singletonMap("body", ticket.get("description")));

            Map<String, Object> request = Collections.singletonMap("ticket", ticketData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", authHeader);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
            System.out.println("Data sent!!!");
            return restTemplate.exchange(
                    "https://z3nzendeskcodingchallengehelp.zendesk.com/api/v2/tickets",
                    HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


}
