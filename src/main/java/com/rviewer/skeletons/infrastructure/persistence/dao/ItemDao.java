package com.rviewer.skeletons.infrastructure.persistence.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Builder
@Table(name = "ITEM")
@FieldNameConstants
public class ItemDao {

    @Id
    @Column(name = "_ID")
    private Long id;

    @Column(name = "_CONTENT")
    private String content;

    @Column(name = "SAFEBOX_ID")
    @ManyToOne(optional = false)
    private SafeboxDao safebox;

}
