package com.danwsaps.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(
        name = "t_book_detail"
)
public class BookDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -509638181821836385L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_detail_generator"
    )
    @SequenceGenerator(
            name = "book_detail_generator",
            sequenceName = "book_detail_id_seq"
    )
    private Long id;

    @Column(
            name = "setting"
    )
    private String setting;

    @Column(
            name = "thumbnail"
    )
    private String thumbnail;

    @OneToOne
    @JoinColumn(
            name = "book_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Book book;

}
