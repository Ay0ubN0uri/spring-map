package com.a00n.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a00n.entities.Position;
import com.a00n.repositories.PositionRepository;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/all")
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @PostMapping("/create")
    public Position create(@RequestBody Position position) {
        return positionRepository.save(position);
    }

    @DeleteMapping("/position/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            System.out.println("hello");
            Position std = positionRepository.findById(id).get();
            System.out.println(std);
            positionRepository.delete(std);
            return ResponseEntity.ok("Position with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Position with ID " + id + " not found.");
        }
    }

    @GetMapping("/count")
    public long count() {
        return positionRepository.count();
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(positionRepository.findById(id).get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Position with ID " + id + " not found.");
        }
    }

    @PutMapping("/position/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Position updatedPosition) {
        try {
            Optional<Position> existingPosition = positionRepository.findById(id);
            if (existingPosition.isPresent()) {
                Position position = existingPosition.get();

                position.setLatitude(updatedPosition.getLatitude());
                position.setLongitude(updatedPosition.getLongitude());
                position.setDate(updatedPosition.getDate());
                position.setImei(updatedPosition.getImei());

                positionRepository.save(position);

                return ResponseEntity.ok(position);
            } else {
                String message = "Position with ID " + id + " not found.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (Exception e) {
            String message = "Position with ID " + id + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }
}
