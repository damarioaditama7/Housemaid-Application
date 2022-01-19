package com.example.Housemaid.Application.controller;

import com.example.Housemaid.Application.WebResponse;
import com.example.Housemaid.Application.entity.Maid;
import com.example.Housemaid.Application.service.MaidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("maids")
public class MaidController {
    private final MaidService maidService;

    @PostMapping()
    public ResponseEntity<Maid> createMaid(@RequestBody Maid request) {
        Maid customer = maidService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{maidId}")
    public ResponseEntity<Maid> getMaidById(@PathVariable("maidId") String id) {
        Maid customer = maidService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping()
    public ResponseEntity<Stream<Maid>> getAllMaid() {
        Stream<Maid> stream = maidService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(stream);
    }

    @GetMapping("/name/{maidName}")
    public ResponseEntity<Stream<Maid>> getMaidByName(@PathVariable("maidName") String name) {
        Stream<Maid> maid = maidService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(maid);
    }

    @DeleteMapping("/{maidId}")
    public ResponseEntity<WebResponse<String>> deleteMaidById(@PathVariable("maidId") String id) {
        String message = maidService.deleteById(id);
        WebResponse<String> response =
                new WebResponse<>(String.format("Delete maid with id: %s", id), message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping()
    public ResponseEntity<Maid> update(@RequestBody Maid request) {
        Maid maid = maidService.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(maid);
    }

    @PutMapping("/enable_status/{maidId}")
    public ResponseEntity<WebResponse<String>> setEnable(@PathVariable("maidId") String maidId){
        String message = maidService.setIsEnable(maidId);
        WebResponse<String> response =
                new WebResponse<>("Successful process", message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
