package be.eafcuccle.projint.backendsirimeraoui.api;

import java.net.URI;
import java.util.Objects;
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

import be.eafcuccle.projint.backendsirimeraoui.Entities.Reservation;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.ReservationRepository;
import be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO.ReservationTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin
public class ReservationController {
  @Autowired
  private ReservationRepository reservationRepository;

  @GetMapping
  public ResponseEntity<Page<ReservationTO>> reservations(int page, int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Reservation> reservations = reservationRepository.findAll(pageRequest);
    Page<ReservationTO> reservationsTO = reservations.map(ReservationController::convertToTO);
    Object[] versions = reservations.stream().map((reservation) -> reservation.getVersion()).toArray();
    int eTag = Objects.hash(versions);
    return ResponseEntity.ok()
        .cacheControl(CacheControl.noCache())
        .eTag(Integer.toString(eTag))
        .body(reservationsTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReservationTO> getReservationById(@PathVariable("id") long id) {
    java.util.Optional<Reservation> reservation = reservationRepository.findById(id);
    if (reservation.isPresent()) {
      ReservationTO reservationTO = convertToTO(reservation.get());
      return ResponseEntity.ok().eTag(Long.toString(reservation.get().getVersion())).body(reservationTO);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(consumes = { "*/*" })
  public ResponseEntity<?> createReservation(@RequestBody ReservationTO ReservationTO,
      UriComponentsBuilder uriBuilder) {
    Long id = reservationRepository.save(convertToOriginal(ReservationTO)).getId();
    URI newReservationUri = uriBuilder.path("{id}").build(id);
    return ResponseEntity.created(newReservationUri).build();
  }

  // converters
  private static Reservation convertToOriginal(ReservationTO reservationTO) {

    return new Reservation(reservationTO.getStatus());
  }

  private static ReservationTO convertToTO(Reservation reservation) {
    return new ReservationTO(reservation.getId(), reservation.getReservationDate(), reservation.getStatus());
  }

}
