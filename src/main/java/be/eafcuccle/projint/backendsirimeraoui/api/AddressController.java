package be.eafcuccle.projint.backendsirimeraoui.api;




import java.net.URI;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import be.eafcuccle.projint.backendsirimeraoui.Entities.Address;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.AddressRepository;
// import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/api/v1/addresses")
@CrossOrigin
public class AddressController {
     @Autowired
     private AddressRepository addressRepository;

     @GetMapping
     public Page<Address> addresses(int page,int size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("town"));
        Page<Address> addresses = addressRepository.findAll(pageRequest);
        return addresses;
     }
     @GetMapping("/{id}")
     public ResponseEntity<Address> getAddressById(@PathVariable("id") long id) {
        Address address = null;
       java.util.Optional<Address> addr = addressRepository.findById(id);
       if (addr.isPresent()) {
          address = addr.get();
          return ResponseEntity.ok().eTag(Long.toString(address.getVersion())).body(address); 
       } else {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
     }

     @PostMapping
     public ResponseEntity<?> createAddress(@RequestBody Address address, UriComponentsBuilder uriBuilder) {
       Long id = addressRepository.save(address).getId();
       URI newAddressUri = uriBuilder.path("{id}").build(id);
       return ResponseEntity.created(newAddressUri).build();
     }


}
