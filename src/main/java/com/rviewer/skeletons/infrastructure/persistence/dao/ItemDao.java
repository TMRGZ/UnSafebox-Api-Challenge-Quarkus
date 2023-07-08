package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("ITEM")
public class ItemDao {

    @Id
    @Column("_ID")
    private Long id;

    @Column("_CONTENT")
    private String content;

    @Column("SAFEBOX_ID")
    private Long safeboxId;

}
