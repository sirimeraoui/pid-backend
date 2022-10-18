package be.eafcuccle.projint.backendsirimeraoui.api;

import be.eafcuccle.projint.backendsirimeraoui.Entities.AirlineCompany;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AirlineCompanyRepository;
import be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO.AirlineCompanyTO;
import java.net.URI;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/airline_companies")
public class AirlineCompanyController {
  @Autowired
  private AirlineCompanyRepository airlineCompanyRepository;

  @GetMapping
  public ResponseEntity<Page<AirlineCompanyTO>> airlineCompanies(int page, int size) {
    Pageable pageRequest = PageRequest.of(page, size, Sort.by("name"));
    Page<AirlineCompany> airlineCompanies = airlineCompanyRepository.findAll(pageRequest);
    Page<AirlineCompanyTO> airlineCompaniesTO = airlineCompanies.map(AirlineCompanyController::convertToTO);
    Object[] versions = airlineCompanies.stream().map((airC) -> airC.getVersion()).toArray();
    int eTag = Objects.hash(versions);
    return ResponseEntity.ok()
        .cacheControl(CacheControl.noCache())
        .eTag(Integer.toString(eTag))
        .body(airlineCompaniesTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AirlineCompanyTO> getAirlineCompanyById(@PathVariable("id") long id) {
    java.util.Optional<AirlineCompany> airC = airlineCompanyRepository.findById(id);
    if (airC.isPresent()) {
      AirlineCompanyTO airlineCompanyTO = convertToTO(airC.get());
      return ResponseEntity.ok().eTag(Long.toString(airC.get().getVersion())).body(airlineCompanyTO);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<?> createAirlineCompany(@RequestBody AirlineCompanyTO airlineCompanyTO,
      UriComponentsBuilder uriBuilder) {
    Long id = airlineCompanyRepository.save(convertToOriginal(airlineCompanyTO)).getId();
    URI newAirlineCompanyUri = uriBuilder.path("{id}").build(id);
    return ResponseEntity.created(newAirlineCompanyUri).build();
  }

  // converters
  private static AirlineCompany convertToOriginal(AirlineCompanyTO airCompanyTO) {

    return new AirlineCompany(airCompanyTO.getName(), airCompanyTO.getMotto());
  }

  private static AirlineCompanyTO convertToTO(AirlineCompany airCompany) {
    return new AirlineCompanyTO(airCompany.getId(), airCompany.getName(), airCompany.getMotto(),
        airCompany.getVersion());
  }
}
