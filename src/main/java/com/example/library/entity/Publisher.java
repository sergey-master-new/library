package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("PUBLISHERS")
public class Publisher {

    @Id
    @Column("PUBLISHER_ID")
    private long id;

    @Column("PUBLISHER_NAME")
    private String name;

}