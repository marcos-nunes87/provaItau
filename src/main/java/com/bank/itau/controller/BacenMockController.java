package com.bank.itau.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BacenMockController {
	@GetMapping("/bacen-api/notify")
    public String notifyBacen() {
        // Simula uma notificação bem-sucedida ao BACEN
        return "Notification sent to BACEN";
    }
}
