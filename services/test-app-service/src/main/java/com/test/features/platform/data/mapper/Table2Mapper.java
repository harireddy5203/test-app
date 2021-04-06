/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.mapper;

import com.test.features.platform.data.mapper.decorator.Table2MapperDecorator;
import com.test.features.platform.data.model.experience.table2.CreateTable2Request;
import com.test.features.platform.data.model.experience.table2.Table2;
import com.test.features.platform.data.model.experience.table2.UpdateTable2Request;
import com.test.features.platform.data.model.persistence.Table2Entity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link Table2Entity to {@link Table2 and vice-versa.
 *
 * @author Mahalingam Iyer
 */
@DecoratedWith(value = Table2MapperDecorator.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface Table2Mapper {

    /**
     * This method transforms an instance of type {@link CreateTable2Request} to an instance of type
     * {@link Table2Entity}.
     *
     * @param source Instance of type {@link CreateTable2Request} that needs to be transformed to
     *     {@link Table2Entity}.
     * @return Instance of type {@link Table2Entity}.
     */
    @Mapping(source = "column4", target = "column4", ignore = true)
    Table2Entity transform(CreateTable2Request source);

    /**
     * This method transforms an instance of type {@link Table2Entity} to an instance of type {@link
     * Table2}.
     *
     * @param source Instance of type {@link Table2Entity} that needs to be transformed to {@link
     *     Table2}.
     * @return Instance of type {@link Table2}.
     */
    @Mapping(source = "column4", target = "column4", ignore = true)
    Table2 transform(Table2Entity source);

    /**
     * This method transforms an instance of type {@link UpdateTable2Request} to an instance of type
     * {@link Table2Entity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateTable2Request} that needs to be transformed to
     *     {@link Table2Entity}.
     * @param target Instance of type {@link Table2Entity} that will be updated instead of creating
     *     and returning a new instance.
     */
    @Mapping(source = "column4", target = "column4", ignore = true)
    void transform(UpdateTable2Request source, @MappingTarget Table2Entity target);

    /**
     * This method transforms an instance of type {@link UpdateTable2Request} to an instance of type
     * {@link Table2Entity}.
     *
     * @param source Instance of type {@link UpdateTable2Request} that needs to be transformed to
     *     {@link Table2Entity}.
     * @return Instance of type {@link Table2Entity}.
     */
    @Mapping(source = "column4", target = "column4", ignore = true)
    Table2Entity transform(UpdateTable2Request source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateTable2Request}
     * instances to a collection of instances of type {@link Table2Entity}.
     *
     * @param source Instance of type {@link UpdateTable2Request} that needs to be transformed to
     *     {@link Table2Entity}.
     * @return Instance of type {@link Table2Entity}.
     */
    default Collection<Table2Entity> transformList(Collection<UpdateTable2Request> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
