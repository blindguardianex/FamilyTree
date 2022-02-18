package org.brutforcer.service.user.controller.impl;

import org.brutforcer.service.user.controller.StatusResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusResourceRest implements StatusResource {

    @Override
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("It work!");
    }
}