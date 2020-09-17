package com.example.demo.entities;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EACQ_MDES_TOKEN", schema = "public")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_eacq_mdes_token", columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "VARCHAR(32)")
    private String type = "CLOUD";

    @Column(nullable = false)
    private String customerKey;

    @Column(nullable = false)
    private String taskId;

    @Column(nullable = false, length = 32)
    private String status = "NEW";

    @Column(length = 64)
    private String tokenUniqueReference;

    @Column(length = 64)
    private String panUniqueReference;

    @Column(length = 8)
    private String tokenPanSuffix;

    @Column(length = 8)
    private String accountPanSuffix;

    @Column(length = 4)
    private String tokenExpiry;

    @Column(length = 4)
    private String accountPanExpiry;

    @Column(length = 1)
    private String dsrpCapable;

    @Column(length = 2)
    private String tokenAssuranceLevel;
}
