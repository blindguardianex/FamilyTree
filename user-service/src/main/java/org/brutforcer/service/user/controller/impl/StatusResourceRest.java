package org.brutforcer.service.user.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.controller.StatusResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StatusResourceRest implements StatusResource {

    @Override
    public ResponseEntity<String> status() {
        log.info("IT WORK!");
        return ResponseEntity.ok("It work!");
    }
}