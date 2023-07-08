package com.rviewer.skeletons.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Safebox {

    private Long id;

    private String name;

    private String password;

}
