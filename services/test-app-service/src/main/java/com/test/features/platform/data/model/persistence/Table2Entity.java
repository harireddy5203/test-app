/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.features.platform.data.model.persistence;

import com.test.commons.data.jpa.persistence.AbstractIdGeneratedEntity;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "table2" table in the database to an entity in the ORM world.
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
@Table(name = "table2")
@Entity
public class Table2Entity extends AbstractIdGeneratedEntity<Integer> {

    /** Reference to the column_1. */
    @Column(name = "column_1", nullable = false)
    private Integer column1;

    /** Reference to the column_2. */
    @Column(name = "column_2", nullable = false)
    private Integer column2;

    /** Reference to the column_3. */
    @Column(name = "column_3", nullable = false)
    private Integer column3;

    /** Reference to the column_4. */
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "table2_id", referencedColumnName = "id")
    private Collection<Table1Entity> column4;
    /** Reference to the column_5. */
    @Column(name = "column_5", nullable = false)
    private Integer column5;

    /**
     * This entity method is used to add an existing Table1 in the system. {@link
     * com.test.features.platform.data.model.persistence.Table1Entity} into the system.
     *
     * @param table1 containing the details required to create an instance of type {@link
     *     com.test.features.platform.data.model.persistence.Table1Entity}.
     */
    public void addTable1(final Table1Entity table1) {
        if (Objects.nonNull(table1)) {
            this.column4.add(table1);
        }
    }

    /**
     * This entity method is used to delete an existing Table1 in the system. instance of {@link
     * com.test.features.platform.data.model.persistence.Table1Entity}}.
     *
     * @param table1Id Unique identifier of Table1.
     */
    public void deleteTable1ById(final Integer table1Id) {

        final Optional<Table1Entity> matchingRecord = findTable1ById(table1Id);
        if (matchingRecord.isPresent()) {
            getColumn4().remove(matchingRecord);
        }
    }

    /**
     * This method is used to find an existing Table1 in the system. instance of {@link
     * com.test.features.platform.data.model.persistence.Table1Entity}}.
     *
     * @param table1Id Unique identifier of Table1.
     * @return Return an instance of type {@link
     *     com.test.features.platform.data.model.persistence.Table1Entity}.
     */
    public Optional<Table1Entity> findTable1ById(final Integer table1Id) {

        if (Objects.isNull(column4) || column4.isEmpty()) {
            return Optional.empty();
        }

        return column4.stream().filter(table1 -> table1.getId().equals(table1Id)).findFirst();
    }

    /**
     * This entity method is used to retrieve the latest Table1 of type {@link
     * com.test.features.platform.data.model.persistence.Table1Entity}.
     *
     * @return Return an instance of type {@link
     *     com.test.features.platform.data.model.persistence.Table1Entity}.
     */
    public Table1Entity getLatestTable1() {
        final List<Table1Entity> column4 = (List<Table1Entity>) this.column4;
        return column4.get(column4.size() - 1);
    }
}
