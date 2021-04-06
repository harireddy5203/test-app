/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.web.api;

import com.test.commons.data.utils.PageUtils;
import com.test.commons.web.api.AbstractApi;
import com.test.commons.web.configuration.properties.ApiDocumentationSettings;
import com.test.features.platform.data.model.experience.table1.CreateTable1Request;
import com.test.features.platform.data.model.experience.table1.Table1;
import com.test.features.platform.data.model.experience.table1.UpdateTable1Request;
import com.test.features.platform.web.service.Table1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of APIs that provide CRUD (Create, Read, Update and Delete) functionality for
 * persistence models of type {@link
 * com.test.features.platform.data.model.persistence.Table1Entity}.
 *
 * @author Mahalingam Iyer
 */
@Slf4j
@RestController
public class Table1Api extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Table1s";

    /** Service implementation of type {@link Table1Service}. */
    private final Table1Service table1Service;

    /**
     * Constructor.
     *
     * @param table1Service Service instance of type {@link Table1Service}.
     */
    public Table1Api(final Table1Service table1Service) {
        this.table1Service = table1Service;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} into the system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.test.features.platform.data.model.persistence.Table1Entity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table1}.
     */
    @Operation(
            method = "createTable1",
            summary = "Create a new Table1.",
            description = "This API is used to create a new Table1 in the system.",
            tags = {Table1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully created a new Table1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/table2s/{table2Id}/table1s")
    public ResponseEntity<Table1> createTable1(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @Valid @RequestBody final CreateTable1Request payload) {
        // Delegate to the service layer.
        final Table1 newInstance = table1Service.createTable1(table2Id, payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @param table1Id Unique identifier of Table1 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Table1, which needs to
     *     be updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table1}.
     */
    @Operation(
            method = "updateTable1",
            summary = "Update an existing Table1.",
            description = "This API is used to update an existing Table1 in the system.",
            tags = {Table1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully updated an existing Table1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/table2s/{table2Id}/table1s/{table1Id}")
    public ResponseEntity<Table1> updateTable1(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @PathVariable(name = "table1Id") final Integer table1Id,
            @Valid @RequestBody final UpdateTable1Request payload) {
        // Delegate to the service layer.
        final Table1 updatedInstance = table1Service.updateTable1(table2Id, table1Id, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @param table1Id Unique identifier of Table1 in the system, whose details have to be
     *     retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table1}.
     */
    @Operation(
            method = "findTable1",
            summary = "Find an existing Table1.",
            description = "This API is used to find an existing Table1 in the system.",
            tags = {Table1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the details of an existing Table1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/table2s/{table2Id}/table1s/{table1Id}")
    public ResponseEntity<Table1> findTable1(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @PathVariable(name = "table1Id") final Integer table1Id) {
        // Delegate to the service layer.
        final Table1 matchingInstance = table1Service.findTable1(table2Id, table1Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} in the system in a paginated
     * manner.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type Table1
     *     based on the provided pagination settings.
     */
    @Operation(
            method = "findAllTable1s",
            summary = "Find all Table1s.",
            description = "This API is used to find all Table1s in the system.",
            tags = {Table1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the Table1s in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/table2s/{table2Id}/table1s")
    public ResponseEntity<Page<Table1>> findAllTable1s(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Table1> matchingInstances = table1Service.findAllTable1s(table2Id, pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @param table1Id Unique identifier of Table1 in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.test.features.platform.data.model.persistence.Table1Entity} that was deleted
     *     from the system.
     */
    @Operation(
            method = "deleteTable1",
            summary = "Delete an existing Table1.",
            description = "This API is used to delete an existing Table1 in the system.",
            tags = {Table1Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully deleted an existing Table1 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/table2s/{table2Id}/table1s/{table1Id}")
    public ResponseEntity<Integer> deleteTable1(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @PathVariable(name = "table1Id") final Integer table1Id) {
        // Delegate to the service layer.
        final Integer deletedInstance = table1Service.deleteTable1(table2Id, table1Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
