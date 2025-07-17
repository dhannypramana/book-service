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
        name = "t_book",
        indexes = {
                @Index(
                        name = "book_secure_id",
                        columnList = "secure_id"
                )
        }
)
@DynamicInsert
@DynamicUpdate
@SQLDelete(
        sql = "UPDATE t_book SET deleted = false WHERE id = ? AND deleted = false"
)
public class Book extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -125709438248790034L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_generator"
    )
    @SequenceGenerator(
            name = "book_generator",
            sequenceName = "book_id_seq"
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            unique = true
    )
    private String title;

    @Column(
            name = "description"
    )
    private String description;

    @OneToOne(
            mappedBy = "book",
            cascade = CascadeType.ALL
    )
    private BookDetail bookDetail;

    @ManyToOne
    @JoinColumn(
            name = "publisher_id",
            nullable = false
    )
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "t_book_category",
            joinColumns = {
                    @JoinColumn(
                            name = "book_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "category_code",
                            referencedColumnName = "code"
                    )
            }
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "t_book_author",
            joinColumns = {
                    @JoinColumn(
                            name = "book_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "author_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private List<Author> authors;

}
