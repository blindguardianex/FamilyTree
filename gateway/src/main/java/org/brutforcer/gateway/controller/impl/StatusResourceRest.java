package org.brutforcer.gateway.controller.impl;

import org.brutforcer.gateway.controller.StatusResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusResourceRest implements StatusResource {

    @Override
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("It work!");
    }
}
