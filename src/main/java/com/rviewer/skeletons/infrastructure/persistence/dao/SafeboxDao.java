package com.rviewer.skeletons.infrastructure.persistence.dao;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Builder
@Table(name = "SAFEBOX")
@UserDefinition
@FieldNameConstants
public class SafeboxDao {

    @Id
    @Column(name = "_ID")
    public Long id;

    @Column(name = "_NAME")
    @Username
    public String name;

    @Column(name = "_PASSWORD")
    @Password
    public String password;

    @OneToMany(orphanRemoval = true)
    @Builder.Default
    private List<ItemDao> itemList = Collections.emptyList();

}
