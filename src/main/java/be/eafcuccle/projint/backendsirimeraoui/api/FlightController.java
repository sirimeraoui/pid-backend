package be.eafcuccle.projint.backendsirimeraoui.api;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.data.domain.PageRequest;
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

import be.eafcuccle.projint.backendsirimeraoui.Entities.Flight;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.FlightRepository;
import be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO.FlightTO;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/flights")
public class FlightController {
  @Autowired
  private FlightRepository flightRepository;

  @GetMapping
  public ResponseEntity<Page<FlightTO>> flights(int page, int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Flight> flights = flightRepository.findAll(pageRequest);
    Page<FlightTO> flightsTO = flights.map(FlightController::convertToTO);
    Object[] versions = flights.stream().map((flight) -> flight.getVersion()).toArray();
    int eTag = Objects.hash(versions);
    return ResponseEntity.ok()
        .cacheControl(CacheControl.noCache())
        .eTag(Integer.toString(eTag))
        .body(flightsTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightTO> getFlightById(@PathVariable("id") long id) {
    java.util.Optional<Flight> flight = flightRepository.findById(id);
    if (flight.isPresent()) {
      FlightTO flightTO = convertToTO(flight.get());
      return ResponseEntity.ok().eTag(Long.toString(flight.get().getVersion())).body(flightTO);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<?> createFlight(@RequestBody FlightTO flightTO, UriComponentsBuilder uriBuilder) {
    Long id = flightRepository.save(convertToOriginal(flightTO)).getId();
    URI newFlightUri = uriBuilder.path("{id}").build(id);
    return ResponseEntity.created(newFlightUri).build();
  }

  // converters
  private static Flight convertToOriginal(FlightTO flightTO) {

    return new Flight(flightTO.getCode(), flightTO.getDepartDate(), flightTO.getDepartTime(),
        flightTO.getArrivalTime());
  }

  private static FlightTO convertToTO(Flight flight) {
    return new FlightTO(flight.getId(), flight.getnbrOfPassengers(), flight.getCode(), flight.getDepartDate(),
        flight.getDepartTime(), flight.getArrivalTime(), flight.getVersion());
  }
}
