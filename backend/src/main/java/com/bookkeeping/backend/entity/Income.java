package com.bookkeeping.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "income")
public class Income {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @Column(name = "year", nullable = false)
    private Integer year;

    @OneToOne
    @JoinColumn(name = "months_id", referencedColumnName = "id")
    private Months months;
}
