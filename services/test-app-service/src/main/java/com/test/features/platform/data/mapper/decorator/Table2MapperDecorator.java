/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.mapper.decorator;

import com.test.features.platform.data.mapper.Table1Mapper;
import com.test.features.platform.data.mapper.Table2Mapper;
import com.test.features.platform.data.model.experience.table2.CreateTable2Request;
import com.test.features.platform.data.model.experience.table2.Table2;
import com.test.features.platform.data.model.experience.table2.UpdateTable2Request;
import com.test.features.platform.data.model.persistence.Table2Entity;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Decorator implementation that maps / transforms data from an instance of type {@link Table2Entity to {@link Table2 and vice-versa.
 *
 * @author Mahalingam Iyer
 */
@Slf4j
public abstract class Table2MapperDecorator implements Table2Mapper {

    /** Mapper implementation of type {@link Table2Mapper}. */
    @Autowired private Table2Mapper table2Mapper;

    /** Mapper implementation of type {@link Table1Mapper}. */
    @Autowired private Table1Mapper table1Mapper;

    @Override
    public Table2Entity transform(CreateTable2Request source) {
        // 1. Transform the CreateTable2Request to Table2Entity object.
        final Table2Entity table2 = table2Mapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getColumn4())) {
            table2.setColumn4(
                    source.getColumn4().stream()
                            .map(table1 -> table1Mapper.transform(table1))
                            .collect(Collectors.toList()));
        }
        // Return the transformed object.
        return table2;
    }

    @Override
    public Table2 transform(Table2Entity source) {
        // 1. Transform the Table2Entity to Table2 object.
        final Table2 table2 = table2Mapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getColumn4())) {
            table2.setColumn4(
                    source.getColumn4().stream()
                            .map(table1 -> table1Mapper.transform(table1))
                            .collect(Collectors.toList()));
        }
        // Return the transformed object.
        return table2;
    }

    @Override
    public void transform(UpdateTable2Request source, @MappingTarget Table2Entity target) {

        // Transform from source to the target.
        table2Mapper.transform(source, target);

        if (!CollectionUtils.isEmpty(source.getColumn4())) {
            target.setColumn4(table1Mapper.transformList(source.getColumn4()));
        }
    }
}
