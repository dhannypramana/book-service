package com.danwsaps.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
        name = "t_author",
        indexes = {
                @Index(
                        name = "author_secure_id",
                        columnList = "secure_id"
                )
        }
)
@DynamicInsert
@DynamicUpdate
@SQLDelete(
        sql = "UPDATE t_author SET deleted = false WHERE id = ? AND deleted = false"
)
public class Author extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -1728276324118483633L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_generator"
    )
    @SequenceGenerator(
            name = "author_generator",
            sequenceName = "author_id_seq"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true,
            length = 100
    )
    private String name;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "birth_date"
    )
    private LocalDate birthDate;

    @ManyToMany(
            mappedBy = "authors"
    )
    private List<Book> books;

}
