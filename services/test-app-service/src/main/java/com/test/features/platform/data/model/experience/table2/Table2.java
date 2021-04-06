/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.model.experience.table2;

import com.test.features.platform.data.model.experience.table1.Table1;
import java.util.Collection;
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
public class Table2 {
    /** Reference to the id. */
    private Integer id;

    /** Reference to the column_1. */
    private Integer column1;

    /** Reference to the column_2. */
    private Integer column2;

    /** Reference to the column_3. */
    private Integer column3;

    /** Reference to the column_4. */
    private Collection<Table1> column4;

    /** Reference to the column_5. */
    private Integer column5;
}
