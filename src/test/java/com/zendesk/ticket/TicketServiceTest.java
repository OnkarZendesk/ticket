package com.zendesk.ticket;
import com.zendesk.ticket.config.CredConfig;
import com.zendesk.ticket.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CredConfig credConfig;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testGetData() {
        Mockito.when(credConfig.getApiUsername()).thenReturn("username");
        Mockito.when(credConfig.getApiPassword()).thenReturn("password");

        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(), eq(String.class)))
                .thenThrow(HttpClientErrorException.create(UNAUTHORIZED, "Unauthorized", null, null, null));

        ResponseEntity<String> responseEntity = ticketService.getData();

        assertEquals(UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testData() {
        Mockito.when(credConfig.getApiUsername()).thenReturn("username");
        Mockito.when(credConfig.getApiPassword()).thenReturn("password");

        Map<String, String> map = new HashMap<>();
        map.put("subject", "test");
        map.put("description", "test");

        // Assuming HttpClientErrorException is thrown
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.POST), any(), eq(String.class)))
                .thenThrow(HttpClientErrorException.create(UNAUTHORIZED, "Unauthorized", null, null, null));

        ResponseEntity<String> responseEntity = ticketService.setData(map);

        assertEquals(UNAUTHORIZED, responseEntity.getStatusCode());
    }
}
