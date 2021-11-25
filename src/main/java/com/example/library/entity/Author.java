package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("AUTHORS")
public class Author {

    @Id
    @Column("AUTHOR_ID")
    private long id;

//    @Column("AUTHOR_NO")
//    private long authorNo;

    @Column("LAST_NAME")
    private String lastName;

    @Column("FIRST_NAME")
    private String firstName;

    @Column("PATRONOMIC")
    private String patronymic;
}