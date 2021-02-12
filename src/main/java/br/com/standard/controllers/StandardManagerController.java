package br.com.standard.controllers;

import br.com.standard.domains.standard.StandardDto;
import br.com.standard.services.standard.StandardManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("standard-manager")
public class StandardManagerController {

    private final StandardManagerService service;

    public StandardManagerController(StandardManagerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StandardDto>> listAllAssistance() {
        return ResponseEntity.ok(service.listAllStandard());
    }

    @PostMapping
    public ResponseEntity<StandardDto> saveAssistance(@RequestBody StandardDto input) {
        return ResponseEntity.ok(service.saveStandard(input));
    }
}
