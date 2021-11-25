package com.example.library.entity.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("PUBLICATION_TYPE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeRef {

    Long typeId;

}

