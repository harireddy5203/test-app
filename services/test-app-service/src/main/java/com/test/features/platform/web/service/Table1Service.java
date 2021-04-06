/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.web.service;

import com.test.commons.data.utils.PageUtils;
import com.test.commons.exception.ServiceException;
import com.test.commons.instrumentation.Instrument;
import com.test.error.Errors;
import com.test.features.platform.data.mapper.Table1Mapper;
import com.test.features.platform.data.model.experience.table1.CreateTable1Request;
import com.test.features.platform.data.model.experience.table1.Table1;
import com.test.features.platform.data.model.experience.table1.UpdateTable1Request;
import com.test.features.platform.data.model.persistence.Table1Entity;
import com.test.features.platform.data.model.persistence.Table2Entity;
import com.test.features.platform.data.repository.Table2Repository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link Table1Entity}.
 *
 * @author Mahalingam Iyer
 */
@Slf4j
@Validated
@Service
public class Table1Service {

    /** Repository implementation of type {@link Table2Repository}. */
    private final Table2Repository table2Repository;

    /** Mapper implementation of type {@link Table1Mapper} to transform between different types. */
    private final Table1Mapper table1Mapper;

    /**
     * Constructor.
     *
     * @param table2Repository Repository instance of type {@link Table2Repository}.
     * @param table1Mapper Mapper instance of type {@link Table1Mapper}.
     */
    public Table1Service(final Table2Repository table2Repository, final Table1Mapper table1Mapper) {
        this.table2Repository = table2Repository;
        this.table1Mapper = table1Mapper;
    }

    /**
     * This method attempts to create an instance of type {@link Table1Entity} in the system based
     * on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     Table1Entity}.
     * @return An experience model of type {@link Table1} that represents the newly created entity
     *     of type {@link Table1Entity}.
     */
    @Instrument
    @Transactional
    public Table1 createTable1(final Integer table2Id, @Valid final CreateTable1Request payload) {
        // 0. Validate the dependencies.

        final Table2Entity matchingTable2 =
                table2Repository
                        .findById(table2Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table2Id));
        // 1. Transform the experience model to a persistence model.
        final Table1Entity table1Entity = table1Mapper.transform(payload);

        // 2. Add the mapped model to parent model.
        matchingTable2.addTable1(table1Entity);

        // 3. Save the entity.
        Table1Service.LOGGER.debug("Saving added instance of type - Table1Entity");
        final Table2Entity updatedInstance = table2Repository.save(matchingTable2);

        // 4. Transform and return.
        return table1Mapper.transform(matchingTable2.getLatestTable1());
    }

    /**
     * This method attempts to update an existing instance of type {@link Table1Entity} using the
     * details from the provided input, which is an instance of type {@link UpdateTable1Request}.
     *
     * @param table1Id Unique identifier of Table1 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Table1, which needs to
     *     be updated in the system.
     * @return A instance of type {@link Table1} containing the updated details.
     */
    @Instrument
    @Transactional
    public Table1 updateTable1(
            final Integer table2Id,
            final Integer table1Id,
            @Valid final UpdateTable1Request payload) {

        // 0. Validate the dependencies.

        final Table2Entity matchingTable2 =
                table2Repository
                        .findById(table2Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table2Id));

        // 1. Verify that the entity being updated truly exists.
        final Table1Entity matchingInstance =
                matchingTable2
                        .findTable1ById(table1Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table1Id));

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        table1Mapper.transform(payload, matchingInstance);

        // 3. Save the entity
        Table1Service.LOGGER.debug("Saving the updated entity - Table2Entity");
        final Table2Entity updatedInstance = table2Repository.save(matchingTable2);

        // 4. Transform updated entity to output object
        return table1Mapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find a {@link Table1Entity} whose unique identifier matches the
     * provided identifier.
     *
     * @param table1Id Unique identifier of Table1 in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link Table1} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Table1 findTable1(final Integer table2Id, final Integer table1Id) {

        // 0. Validate the dependencies.

        final Table2Entity matchingTable2 =
                table2Repository
                        .findById(table2Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table2Id));

        // 1. Verify that the entity being updated truly exists.
        final Table1Entity matchingTable1 =
                matchingTable2
                        .findTable1ById(table1Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table1Id));

        // 2. Transform updated entity to output object
        return table1Mapper.transform(matchingTable1);
    }

    /**
     * This method attempts to find instances of type Table1Entity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Table1}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Table1> findAllTable1s(final Integer table2Id, final Pageable page) {
        // 0. Validate the dependencies.

        final Table2Entity matchingTable2 =
                table2Repository
                        .findById(table2Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table2Id));

        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        Table1Service.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<Table1Entity> pageData =
                new PageImpl(
                        (List) matchingTable2.getColumn4(),
                        pageSettings,
                        matchingTable2.getColumn4().size());

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Table1> dataToReturn =
                    pageData.getContent().stream()
                            .map(table1Mapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }
        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link Table1Entity} whose unique
     * identifier matches the provided identifier.
     *
     * @param table1Id Unique identifier of Table1 in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type Table1Entity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteTable1(final Integer table2Id, final Integer table1Id) {

        // 0. Validate the dependencies.

        final Table2Entity matchingTable2 =
                table2Repository
                        .findById(table2Id)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, table2Id));

        // 1. Remove the matchingInstance form its parent.
        matchingTable2.deleteTable1ById(table1Id);

        // 2. Persist the parentInstance.
        table2Repository.save(matchingTable2);

        // 3. Return the deleted identifier.
        return table1Id;
    }
}
