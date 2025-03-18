package com.example.demotbl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "BNMAIE01_ENTIDAD",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_B01_ID_ENT_HOS",
                        columnNames = {"B01_ID_ENT_HOS"}
                )
        }
)
@SequenceGenerator(
        name = "BNSQ_01_ENTIDAD",
        sequenceName = "BNSQ_01_ENTIDAD",
        allocationSize = 1
)
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BNSQ_01_ENTIDAD")
    @Column(
            name = "B01_ID_ENT",
            nullable = false,
            updatable = false,
            columnDefinition = "NUMBER(10)"
    )
    private Long id;

    @Column(
            name = "B01_ID_ENT_HOS",
            nullable = false,
            length = 50,
            columnDefinition = "VARCHAR2(50)"
    )
    private String idHost;

    @Column(
            name = "B01_DES_ENT",
            nullable = false,
            length = 150,
            columnDefinition = "VARCHAR2(150)"
    )
    private String descEntidad;

    @Column(
            name = "B01_IND_ACT",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private boolean activo;

    @Column(
            name = "B01_FEC_CRE",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT SYSDATE"
    )
    private LocalDateTime fechaCreacion;

    @Column(
            name = "B01_USU_CRE",
            nullable = false,
            updatable = false,
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioCreacion;

    @Column(
            name = "B01_FEC_MOD",
            columnDefinition = "DATE"
    )
    private LocalDateTime fechaModificacion;

    @Column(
            name = "B01_USU_MOD",
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioModificacion;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now().withNano(0);
        }
        if (usuarioCreacion == null) {
            usuarioCreacion = "SYSTEM";
        }
        if (!activo) {
            activo = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now().withNano(0);
        if (usuarioModificacion == null) {
            usuarioModificacion = "SYSTEM";
        }
    }
}

