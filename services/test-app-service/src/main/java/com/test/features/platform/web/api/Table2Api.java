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
import com.test.features.platform.data.model.experience.table2.CreateTable2Request;
import com.test.features.platform.data.model.experience.table2.Table2;
import com.test.features.platform.data.model.experience.table2.UpdateTable2Request;
import com.test.features.platform.web.service.Table2Service;
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
 * com.test.features.platform.data.model.persistence.Table2Entity}.
 *
 * @author Mahalingam Iyer
 */
@Slf4j
@RestController
public class Table2Api extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Table2s";

    /** Service implementation of type {@link Table2Service}. */
    private final Table2Service table2Service;

    /**
     * Constructor.
     *
     * @param table2Service Service instance of type {@link Table2Service}.
     */
    public Table2Api(final Table2Service table2Service) {
        this.table2Service = table2Service;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.test.features.platform.data.model.persistence.Table2Entity} into the system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.test.features.platform.data.model.persistence.Table2Entity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table2}.
     */
    @Operation(
            method = "createTable2",
            summary = "Create a new Table2.",
            description = "This API is used to create a new Table2 in the system.",
            tags = {Table2Api.API_TAG},
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
                        description = "Successfully created a new Table2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/table2s")
    public ResponseEntity<Table2> createTable2(
            @Valid @RequestBody final CreateTable2Request payload) {
        // Delegate to the service layer.
        final Table2 newInstance = table2Service.createTable2(payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.test.features.platform.data.model.persistence.Table2Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Table2, which needs to
     *     be updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table2}.
     */
    @Operation(
            method = "updateTable2",
            summary = "Update an existing Table2.",
            description = "This API is used to update an existing Table2 in the system.",
            tags = {Table2Api.API_TAG},
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
                        description = "Successfully updated an existing Table2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/table2s/{table2Id}")
    public ResponseEntity<Table2> updateTable2(
            @PathVariable(name = "table2Id") final Integer table2Id,
            @Valid @RequestBody final UpdateTable2Request payload) {
        // Delegate to the service layer.
        final Table2 updatedInstance = table2Service.updateTable2(table2Id, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.test.features.platform.data.model.persistence.Table2Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Table2}.
     */
    @Operation(
            method = "findTable2",
            summary = "Find an existing Table2.",
            description = "This API is used to find an existing Table2 in the system.",
            tags = {Table2Api.API_TAG},
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
                                "Successfully retrieved the details of an existing Table2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/table2s/{table2Id}")
    public ResponseEntity<Table2> findTable2(
            @PathVariable(name = "table2Id") final Integer table2Id) {
        // Delegate to the service layer.
        final Table2 matchingInstance = table2Service.findTable2(table2Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.test.features.platform.data.model.persistence.Table2Entity} in the system in a paginated
     * manner.
     *
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type Table2
     *     based on the provided pagination settings.
     */
    @Operation(
            method = "findAllTable2s",
            summary = "Find all Table2s.",
            description = "This API is used to find all Table2s in the system.",
            tags = {Table2Api.API_TAG},
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
                                "Successfully retrieved the Table2s in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/table2s")
    public ResponseEntity<Page<Table2>> findAllTable2s(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Table2> matchingInstances = table2Service.findAllTable2s(pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.test.features.platform.data.model.persistence.Table2Entity} in the system.
     *
     * @param table2Id Unique identifier of Table2 in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.test.features.platform.data.model.persistence.Table2Entity} that was deleted
     *     from the system.
     */
    @Operation(
            method = "deleteTable2",
            summary = "Delete an existing Table2.",
            description = "This API is used to delete an existing Table2 in the system.",
            tags = {Table2Api.API_TAG},
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
                        description = "Successfully deleted an existing Table2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/table2s/{table2Id}")
    public ResponseEntity<Integer> deleteTable2(
            @PathVariable(name = "table2Id") final Integer table2Id) {
        // Delegate to the service layer.
        final Integer deletedInstance = table2Service.deleteTable2(table2Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
