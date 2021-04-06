/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.model.experience.table2;

import com.test.features.platform.data.model.experience.table1.UpdateTable1Request;
import java.util.Collection;
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
public class UpdateTable2Request {
    /** Reference to the id. */
    @NotNull(message = "table2.id.not.null.message")
    private Integer id;

    /** Reference to the column_1. */
    @NotNull(message = "table2.column1.not.null.message")
    @Min(value = 10, message = "table2.column1.min.message")
    @Max(value = 20, message = "table2.column1.max.message")
    private Integer column1;

    /** Reference to the column_2. */
    @NotNull(message = "table2.column2.not.null.message")
    @Min(value = 10, message = "table2.column2.min.message")
    @Max(value = 20, message = "table2.column2.max.message")
    private Integer column2;

    /** Reference to the column_3. */
    @NotNull(message = "table2.column3.not.null.message")
    @Min(value = 10, message = "table2.column3.min.message")
    @Max(value = 20, message = "table2.column3.max.message")
    private Integer column3;

    /** Reference to the column_4. */
    @NotNull(message = "table2.column4.not.null.message")
    private Collection<UpdateTable1Request> column4;

    /** Reference to the column_5. */
    @NotNull(message = "table2.column5.not.null.message")
    @Min(value = 10, message = "table2.column5.min.message")
    @Max(value = 20, message = "table2.column5.max.message")
    private Integer column5;
}
