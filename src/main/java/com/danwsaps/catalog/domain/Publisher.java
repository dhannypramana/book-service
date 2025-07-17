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
        name = "t_publisher",
        indexes = {
                @Index(
                        name = "publisher_secure_id",
                        columnList = "secure_id"
                )
        }
)
@DynamicInsert
@DynamicUpdate
@SQLDelete(
        sql = "UPDATE t_publisher SET deleted = true WHERE id = ? AND deleted = false"
)
public class Publisher extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 2135784547315618712L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_generator"
    )
    @SequenceGenerator(
            name = "publisher_generator",
            sequenceName = "publisher_id_seq"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "company_name"
    )
    private String companyName;

    @Column(
            name = "address"
    )
    private String address;

    @OneToMany(
            mappedBy = "publisher"
    )
    private List<Book> books;

}
