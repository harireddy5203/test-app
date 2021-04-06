/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.model.experience.table1;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author Mahalingam Iyer
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateTable1Request {
    /** Reference to the column_1. */
    @NotNull(message = "table1.column1.not.null.message")
    @Min(value = 10, message = "table1.column1.min.message")
    @Max(value = 20, message = "table1.column1.max.message")
    private Integer column1;

    /** Reference to the column_2. */
    @NotNull(message = "table1.column2.not.null.message")
    @Min(value = 10, message = "table1.column2.min.message")
    @Max(value = 20, message = "table1.column2.max.message")
    private Integer column2;

    /** Reference to the column_3. */
    @NotNull(message = "table1.column3.not.null.message")
    @Min(value = 10, message = "table1.column3.min.message")
    @Max(value = 20, message = "table1.column3.max.message")
    private Integer column3;
}
