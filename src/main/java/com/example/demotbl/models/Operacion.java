package com.example.demotbl.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "BNMAIE02_OPERACION",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_B02_ID_ENT_OPE",
                        columnNames = {"B01_ID_ENT", "B02_ID_OPE_HOS"}
                )
        }
)
@SequenceGenerator(
        name = "BNSQ_02_OPERACION",
        sequenceName = "BNSQ_02_OPERACION",
        allocationSize = 1
)
public class Operacion {

    // ✅ ID generado por secuencia
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BNSQ_02_OPERACION")
    @Column(
            name = "B02_ID",
            nullable = false,
            updatable = false,
            columnDefinition = "NUMBER(10)"
    )
    private Long id;

    // ✅ Relación con Entidad (Foreign Key)
    @ManyToOne
    @JoinColumn(name = "B01_ID_ENT", nullable = false)
    private Entidad entidad;

    // ✅ Código de operación único por entidad
    @Column(
            name = "B02_ID_OPE_HOS",
            nullable = false,
            length = 2,
            columnDefinition = "VARCHAR2(2)"
    )
    private String codigo;

    // ✅ Descripción de la operación
    @Column(
            name = "B02_DES_OPER",
            nullable = false,
            length = 150,
            columnDefinition = "VARCHAR2(150)"
    )
    private String descripcion;

    // ✅ Endpoint (URL)
    @Column(
            name = "B02_END_PNT",
            nullable = false,
            length = 255,
            columnDefinition = "VARCHAR2(255)"
    )
    private String endpoint;

    // ✅ Tipo de operación (1 = GET, 2 = POST, 3 = PUT, 4 = DELETE) → Por defecto POST (2)
    @Column(
            name = "B02_TIP_OPE",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 2"
    )
    private Integer tipoOperacion;

    // ✅ Tipo de respuesta (1 = JSON, 2 = XML, 3 = TEXT) → Por defecto JSON (1)
    @Column(
            name = "B02_TIP_RES",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private Integer tipoRespuesta;

    // ✅ Tiempo de timeout de conexión (en milisegundos)
    @Column(
            name = "B02_TIM_OUT_CX",
            columnDefinition = "NUMBER(10)"
    )
    private Integer timeoutConexion;

    // ✅ Número máximo de reintentos (valor por defecto = 3)
    @Column(
            name = "B02_NUM_RETRY",
            columnDefinition = "NUMBER(2) DEFAULT 3"
    )
    private Integer numeroReintentos;

    // ✅ Estado activo o inactivo
    @Column(
            name = "B02_IND_ACT",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private boolean indActivo;

    // ✅ Fecha de creación
    @Column(
            name = "B02_FEC_CRE",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT SYSDATE"
    )
    private LocalDateTime fechaCreacion;

    // ✅ Usuario de creación
    @Column(
            name = "B02_USU_CRE",
            nullable = false,
            updatable = false,
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioCreacion;

    // ✅ Fecha de modificación
    @Column(
            name = "B02_FEC_MOD",
            columnDefinition = "DATE"
    )
    private LocalDateTime fechaModificacion;

    // ✅ Usuario de modificación
    @Column(
            name = "B02_USU_MOD",
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioModificacion;

    // 🔥 Asignar valores por defecto antes de insertar el registro
    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now().withNano(0);
        }
        if (usuarioCreacion == null) {
            usuarioCreacion = "SYSTEM";
        }
        if (numeroReintentos == null) {
            numeroReintentos = 3;
        }
        if (tipoOperacion == null) {
            tipoOperacion = 2; // POST por defecto
        }
        if (tipoRespuesta == null) {
            tipoRespuesta = 1; // JSON por defecto
        }
        if (!indActivo) {
            indActivo = true;
        }
    }

    // 🔥 Actualizar valores antes de modificar el registro
    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now().withNano(0);
        if (usuarioModificacion == null) {
            usuarioModificacion = "SYSTEM";
        }
    }
}
