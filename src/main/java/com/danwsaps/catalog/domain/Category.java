package com.danwsaps.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
        name = "t_category",
        indexes = {
                @Index(
                        name = "category_secure_id",
                        columnList = "secure_id"
                )
        }
)
@DynamicInsert
@DynamicUpdate
@SQLDelete(
        sql = "UPDATE t_category SET deleted = true WHERE id = ? AND deleted = false"
)
public class Category extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 8026573272180160388L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_generator"
    )
    @SequenceGenerator(
            name = "category_generator",
            sequenceName = "category_id_seq"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "description"
    )
    private String description;

    @ManyToMany(
            mappedBy = "categories"
    )
    private List<Book> books;

}
