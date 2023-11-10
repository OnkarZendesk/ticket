package com.zendesk.ticket.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
//import java.util.logging.Logger;
import com.zendesk.ticket.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/getData")
    public ResponseEntity<String> getData() {
        return ticketService.getData();
    }

    @PostMapping("/postData")
    public ResponseEntity<String> postData(@RequestBody Map<String, String> ticket) {
        return ticketService.setData(ticket);
    }
}
