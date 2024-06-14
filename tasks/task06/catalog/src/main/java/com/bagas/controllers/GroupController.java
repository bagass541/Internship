package com.bagas.controllers;

import com.bagas.dto.GroupDTO;
import com.bagas.exceptions.GroupNotFoundException;
import com.bagas.services.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bagas.constants.CommonConstants.CREATE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.DELETE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.ID_PATH_VARIABLE;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.UPDATE_GROUP_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_DOMAIN)
public class GroupController {

    private final GroupService groupService;

    @GetMapping(GROUP_ENDPOINT)
    public ResponseEntity<List<GroupDTO>> getGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @PostMapping(CREATE_GROUP_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public GroupDTO createGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.create(groupDTO);
    }

    @PutMapping(UPDATE_GROUP_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public GroupDTO updateGroup(@PathVariable(ID_PATH_VARIABLE) Long id, @RequestBody GroupDTO groupDTO) {
        try {
            return groupService.update(id, groupDTO);
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DELETE_GROUP_ENDPOINT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public HttpStatus deleteGroup(@PathVariable(ID_PATH_VARIABLE) Long id) {
        groupService.deleteById(id);
        return HttpStatus.OK;
    }
}
