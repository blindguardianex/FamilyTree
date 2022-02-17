package org.brutforcer.user.controller.impl;

import org.brutforcer.user.controller.StatusResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusResourceRest implements StatusResource {

    @Override
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("It work!");
    }
}