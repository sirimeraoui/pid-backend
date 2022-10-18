package be.eafcuccle.projint.backendsirimeraoui.api;



import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import be.eafcuccle.projint.backendsirimeraoui.Entities.FlightClass;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.FlightClassRepository;




@RestController
@CrossOrigin
@RequestMapping("/api/v1/flight_classes")
public class FlightClassController {
    

    @Autowired
    private FlightClassRepository flightClassRepository;

    @GetMapping
    public Page<FlightClass> FlightClasses(int page,int size)  {
      Pageable pageRequest = PageRequest.of(page, size);
      Page<FlightClass> flightClasses = flightClassRepository.findAll(pageRequest);
      return flightClasses;
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightClass> getFlightClassById(@PathVariable("id") long id) {
       FlightClass flightClass = null;
      java.util.Optional<FlightClass> flightC = flightClassRepository.findById(id);
      if (flightC.isPresent()) {
         flightClass = flightC.get();
         return ResponseEntity.ok().eTag(Long.toString(flightClass.getVersion())).body(flightClass); 
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<?> createFlightClass(@RequestBody FlightClass flightClass, UriComponentsBuilder uriBuilder) {
      Long id = flightClassRepository.save(flightClass).getId();
      URI newFlightClassUri = uriBuilder.path("{id}").build(id);
      return ResponseEntity.created(newFlightClassUri).build();
    }

}
