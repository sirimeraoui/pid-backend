package be.eafcuccle.projint.backendsirimeraoui.api;



import java.net.URI;
import java.util.List;


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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import be.eafcuccle.projint.backendsirimeraoui.Entities.Role;
import be.eafcuccle.projint.backendsirimeraoui.Repositories.RoleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;



@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public Page<Role> roles(int page,int size)  {
      Pageable pageRequest = PageRequest.of(page, size);
      Page<Role> roles = roleRepository.findAll(pageRequest);
      return roles;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") long id) {
       Role Role = null;
      java.util.Optional<Role> addr = roleRepository.findById(id);
      if (addr.isPresent()) {
         Role = addr.get();
         return ResponseEntity.ok().eTag(Long.toString(Role.getVersion())).body(Role); 
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role, UriComponentsBuilder uriBuilder) {
      Long id = roleRepository.save(role).getId();
      URI newRoleUri = uriBuilder.path("{id}").build(id);
      return ResponseEntity.created(newRoleUri).build();
    }


}
