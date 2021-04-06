/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.model.persistence;

import com.test.commons.data.jpa.persistence.AbstractIdGeneratedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "table1" table in the database to an entity in the ORM world.
 *
 * @author Mahalingam Iyer
 */
@EqualsAndHashCode(
        callSuper = true,
        of = {})
@ToString(
        callSuper = true,
        of = {})
@Getter
@Setter
@NoArgsConstructor
@Table(name = "table1")
@Entity
public class Table1Entity extends AbstractIdGeneratedEntity<Integer> {

    /** Reference to the column_1. */
    @Column(name = "column_1", nullable = false)
    private Integer column1;

    /** Reference to the column_2. */
    @Column(name = "column_2", nullable = false)
    private Integer column2;

    /** Reference to the column_3. */
    @Column(name = "column_3", nullable = false)
    private Integer column3;
}
