/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.web.service;

import com.test.commons.data.utils.PageUtils;
import com.test.commons.instrumentation.Instrument;
import com.test.features.platform.data.mapper.Table2Mapper;
import com.test.features.platform.data.model.experience.table2.CreateTable2Request;
import com.test.features.platform.data.model.experience.table2.Table2;
import com.test.features.platform.data.model.experience.table2.UpdateTable2Request;
import com.test.features.platform.data.model.persistence.Table2Entity;
import com.test.features.platform.data.repository.Table2Repository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link Table2Entity}.
 *
 * @author Mahalingam Iyer
 */
@Slf4j
@Validated
@Service
public class Table2Service {
    /** Repository implementation of type {@link Table2Repository}. */
    private final Table2Repository table2Repository;

    /** Mapper implementation of type {@link Table2Mapper} to transform between different types. */
    private final Table2Mapper table2Mapper;

    /**
     * Constructor.
     *
     * @param table2Repository Repository instance of type {@link Table2Repository}.
     * @param table2Mapper Mapper instance of type {@link Table2Mapper}.
     */
    public Table2Service(final Table2Repository table2Repository, final Table2Mapper table2Mapper) {
        this.table2Repository = table2Repository;
        this.table2Mapper = table2Mapper;
    }

    /**
     * This method attempts to create an instance of type {@link Table2Entity} in the system based
     * on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     Table2Entity}.
     * @return An experience model of type {@link Table2} that represents the newly created entity
     *     of type {@link Table2Entity}.
     */
    @Instrument
    @Transactional
    public Table2 createTable2(@Valid final CreateTable2Request payload) {
        // 1. Transform the experience model to a persistence model.
        final Table2Entity table2Entity = table2Mapper.transform(payload);

        // 2. Save the entity.
        Table2Service.LOGGER.debug("Saving a new instance of type - Table2Entity");
        final Table2Entity newInstance = table2Repository.save(table2Entity);

        // 3. Transform the created entity to an experience model and return it.
        return table2Mapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link Table2Entity} using the
     * details from the provided input, which is an instance of type {@link UpdateTable2Request}.
     *
     * @param table2Id Unique identifier of Table2 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Table2, which needs to
     *     be updated in the system.
     * @return A instance of type {@link Table2} containing the updated details.
     */
    @Instrument
    @Transactional
    public Table2 updateTable2(final Integer table2Id, @Valid final UpdateTable2Request payload) {
        // 1. Verify that the entity being updated truly exists.
        final Table2Entity matchingInstance = table2Repository.findByIdOrThrow(table2Id);

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        table2Mapper.transform(payload, matchingInstance);

        // 3. Save the entity
        Table2Service.LOGGER.debug("Saving the updated entity - Table2Entity");
        final Table2Entity updatedInstance = table2Repository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return table2Mapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link Table2Entity} whose unique identifier matches the
     * provided identifier.
     *
     * @param table2Id Unique identifier of Table2 in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link Table2} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Table2 findTable2(final Integer table2Id) {
        // 1. Find a matching entity and throw an exception if not found.
        final Table2Entity matchingInstance = table2Repository.findByIdOrThrow(table2Id);

        // 2. Transform the matching entity to the desired output.
        return table2Mapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type Table2Entity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Table2}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Table2> findAllTable2s(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        Table2Service.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<Table2Entity> pageData = table2Repository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Table2> dataToReturn =
                    pageData.getContent().stream()
                            .map(table2Mapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link Table2Entity} whose unique
     * identifier matches the provided identifier.
     *
     * @param table2Id Unique identifier of Table2 in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type Table2Entity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteTable2(final Integer table2Id) {
        // 1. Delegate to our repository method to handle the deletion.
        return table2Repository.deleteOne(table2Id);
    }
}
