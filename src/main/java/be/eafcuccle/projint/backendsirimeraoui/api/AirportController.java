package be.eafcuccle.projint.backendsirimeraoui.api;



import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Airport;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AirportRepository;

@RestController
@RequestMapping("/api/v1/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public Page<Airport> Airports(int page,int size) {
      Pageable pageRequest = PageRequest.of(page, size);
      Page<Airport> airports = airportRepository.findAll(pageRequest);
      return airports;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable("id") long id) {
       Airport airport = null;
      java.util.Optional<Airport> airP = airportRepository.findById(id);
      if (airP.isPresent()) {
         airport = airP.get();
         return ResponseEntity.ok().eTag(Long.toString(airport.getVersion())).body(airport); 
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<?> createAirport(@RequestBody Airport Airport, UriComponentsBuilder uriBuilder) {
      Long id = airportRepository.save(Airport).getId();
      URI newAirportUri = uriBuilder.path("{id}").build(id);
      return ResponseEntity.created(newAirportUri).build();
    }


    
}
