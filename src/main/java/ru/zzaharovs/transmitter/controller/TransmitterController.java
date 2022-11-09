package ru.zzaharovs.transmitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.zzaharovs.transmitter.model.EmployeeDto;

@RestController
@RequestMapping("/translator")
@RequiredArgsConstructor
public class TransmitterController {

    private final RestTemplate restTemplate;

    @GetMapping("getEcho")
    public String getEcho() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8787/api/echo?param=hello", String.class);
        return response.getBody();
    }

    @PostMapping("setPrintableDto")
    public ResponseEntity<?> setDto(@RequestBody EmployeeDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmployeeDto> entity = new HttpEntity<>(dto, headers);
        restTemplate.postForEntity("http://localhost:8787/api/print", entity, EmployeeDto.class);

        return ResponseEntity.ok()
                .build();
    }


}
