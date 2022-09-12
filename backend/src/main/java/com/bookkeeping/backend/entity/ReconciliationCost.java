package com.bookkeeping.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "recon_cost")
public class ReconciliationCost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @OneToOne
    @JoinColumn(name = "months_id", referencedColumnName = "id")
    private Months months;

    @Column(nullable = false)
    private String type;
}