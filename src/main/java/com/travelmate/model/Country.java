package com.travelmate.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity {

    private String name;

    private String code;
}
