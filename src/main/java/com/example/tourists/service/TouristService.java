package com.example.tourists.service;

import com.example.tourists.model.Tourist;
import com.example.tourists.repository.TouristRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TouristService {
    private final TouristRepository repository;
    public TouristService(TouristRepository repository) { this.repository = repository; }
    public List<Tourist> findAll() { return repository.findAll(); }
    public Tourist findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tourist not found: " + id));
    }
    public Tourist update(Long id, Tourist input) {
        Tourist existing = findById(id);
        existing.setFirstName(input.getFirstName());
        existing.setLastName(input.getLastName());
        existing.setEmail(input.getEmail());
        existing.setPhone(input.getPhone());
        existing.setCountry(input.getCountry());
        existing.setCity(input.getCity());
        return repository.save(existing);
    }
}
