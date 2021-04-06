/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.mapper;

import com.test.features.platform.data.model.experience.table1.CreateTable1Request;
import com.test.features.platform.data.model.experience.table1.Table1;
import com.test.features.platform.data.model.experience.table1.UpdateTable1Request;
import com.test.features.platform.data.model.persistence.Table1Entity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link Table1Entity to {@link Table1 and vice-versa.
 *
 * @author Mahalingam Iyer
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface Table1Mapper {

    /**
     * This method transforms an instance of type {@link CreateTable1Request} to an instance of type
     * {@link Table1Entity}.
     *
     * @param source Instance of type {@link CreateTable1Request} that needs to be transformed to
     *     {@link Table1Entity}.
     * @return Instance of type {@link Table1Entity}.
     */
    Table1Entity transform(CreateTable1Request source);

    /**
     * This method transforms an instance of type {@link Table1Entity} to an instance of type {@link
     * Table1}.
     *
     * @param source Instance of type {@link Table1Entity} that needs to be transformed to {@link
     *     Table1}.
     * @return Instance of type {@link Table1}.
     */
    Table1 transform(Table1Entity source);

    /**
     * This method transforms an instance of type {@link UpdateTable1Request} to an instance of type
     * {@link Table1Entity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateTable1Request} that needs to be transformed to
     *     {@link Table1Entity}.
     * @param target Instance of type {@link Table1Entity} that will be updated instead of creating
     *     and returning a new instance.
     */
    void transform(UpdateTable1Request source, @MappingTarget Table1Entity target);

    /**
     * This method transforms an instance of type {@link UpdateTable1Request} to an instance of type
     * {@link Table1Entity}.
     *
     * @param source Instance of type {@link UpdateTable1Request} that needs to be transformed to
     *     {@link Table1Entity}.
     * @return Instance of type {@link Table1Entity}.
     */
    Table1Entity transform(UpdateTable1Request source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateTable1Request}
     * instances to a collection of instances of type {@link Table1Entity}.
     *
     * @param source Instance of type {@link UpdateTable1Request} that needs to be transformed to
     *     {@link Table1Entity}.
     * @return Instance of type {@link Table1Entity}.
     */
    default Collection<Table1Entity> transformList(Collection<UpdateTable1Request> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
