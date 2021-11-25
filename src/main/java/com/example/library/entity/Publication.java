package com.example.library.entity;

import com.example.library.entity.ref.AuthorRef;
import com.example.library.entity.ref.PublisherRef;
import com.example.library.entity.ref.ThemeRef;
import com.example.library.entity.ref.TypeRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("PUBLICATIONS")
public class Publication {

    @Id
    @Column("PUBLICATION_ID")
    private long id;

    @MappedCollection(idColumn = "PUBLICATION_ID")
    private TypeRef typeRef;

    @MappedCollection(idColumn = "PUBLICATION_ID")
    private ThemeRef themeRef;

    @Column("PUBLICATION_NAME")
    private String name;

    @MappedCollection(idColumn = "PUBLICATION_ID")
    private Set<AuthorRef> authors = new HashSet<>();

    @Column("DATE_OF_PUBLICATION")
    private LocalDate date;

    @MappedCollection(idColumn = "PUBLICATION_ID")
    private PublisherRef publisherRef;

    @Column("PAGE_COUNT")
    private int pageCount;

}
