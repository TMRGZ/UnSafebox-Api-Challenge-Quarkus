package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("SAFEBOX")
public class SafeboxDao {

    @Id
    @Column("_ID")
    public Long id;

    @Column("_NAME")
    public String name;

    @Column("_PASSWORD")
    public String password;

}
