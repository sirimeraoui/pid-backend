package be.eafcuccle.projint.backendsirimeraoui.api;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import be.eafcuccle.projint.backendsirimeraoui.Entities.ContactInfo;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.ContactInfoRepository;
import be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO.ContactInfoTO;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/contacts_info")
public class ContactInfoController {
    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @GetMapping
    public ResponseEntity<Page<ContactInfoTO>> contactsInfo(int page,int size) {
      Pageable pageRequest = PageRequest.of(page, size);
      Page<ContactInfo> contactsInfo = contactInfoRepository.findAll(pageRequest);
      Page<ContactInfoTO> contactsInfoTO = contactsInfo.map(ContactInfoController::convertToTO);
      Object[] versions = contactsInfo.stream().map((airC)-> airC.getVersion()).toArray();
      int eTag = Objects.hash(versions);
      return ResponseEntity.ok()
      .cacheControl(CacheControl.noCache())
      .eTag(Integer.toString(eTag))
      .body(contactsInfoTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactInfoTO> getContactInfoById(@PathVariable("id") Long id) {
      java.util.Optional<ContactInfo> contactInfo = contactInfoRepository.findById(id);
      if (contactInfo.isPresent()) {
         ContactInfoTO contactInfoTO = convertToTO(contactInfo.get());
         return ResponseEntity.ok().eTag(Long.toString(contactInfo.get().getVersion())).body(contactInfoTO); 
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<?> createContactInfo(@RequestBody ContactInfoTO ContactInfoTO, UriComponentsBuilder uriBuilder) {
      Long id = contactInfoRepository.save(convertToOriginal(ContactInfoTO)).getId();
      URI newContactInfoUri = uriBuilder.path("{id}").build(id);
      return ResponseEntity.created(newContactInfoUri).build();
    }


    //converters
    private static ContactInfo convertToOriginal(ContactInfoTO contactInfoTO){

          return new ContactInfo(contactInfoTO.getPhoneNumber(),contactInfoTO.getEmailAddress());
    }

    private static ContactInfoTO convertToTO(ContactInfo contactInfo){
        return new ContactInfoTO(contactInfo.getId(),contactInfo.getPhoneNumber(),contactInfo.getEmailAddress(),contactInfo.getVersion());
      }
}
