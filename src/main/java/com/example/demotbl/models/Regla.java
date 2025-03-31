package com.example.demotbl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "BNMAIE03_REGLA",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_B03_ID_OPER_NOM_CAMPO", // Clave compuesta única
                        columnNames = {"B03_COD_OPER", "B03_NOM_CAMPO"}
                )
        }
)
@SequenceGenerator(
        name = "BNSQ_03_REGLA",         // Nombre de la secuencia en JPA
        sequenceName = "BNSQ_03_REGLA", // Nombre real de la secuencia en Oracle
        allocationSize = 1
)
public class Regla {

    // ✅ ID generado mediante secuencia personalizada
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BNSQ_03_REGLA")
    @Column(
            name = "B03_COD_REGLA",
            nullable = false,
            updatable = false,
            columnDefinition = "NUMBER(10)"
    )
    private Long id;

    // ✅ Relación con Operacion (Foreign Key)
    @ManyToOne
    @JoinColumn(
            name = "B03_COD_OPER", // Nombre de la columna en la tabla BNMAIE03_REGLA
            nullable = false
    )
    private Operacion operacion;

    // ✅ Posición inicial → Obligatorio
    @Column(
            name = "B03_POS_INICIAL",
            nullable = false,
            columnDefinition = "NUMBER(5)"
    )
    private Integer posicionInicial;

    // ✅ Longitud del campo → Por defecto = 0
    @Column(
            name = "B03_LONG_CAMPO",
            nullable = false,
            columnDefinition = "NUMBER(10) DEFAULT 0"
    )
    private Integer longitudCampo;

    // ✅ Nombre del campo → Hasta 100 caracteres
    @Column(
            name = "B03_NOM_CAMPO",
            nullable = false,
            length = 100,
            columnDefinition = "VARCHAR2(100)"
    )
    private String nombreCampo;

    // ✅ Etiqueta del campo → Hasta 100 caracteres
    @Column(
            name = "B03_ETI_CAMPO",
            nullable = false,
            length = 100,
            columnDefinition = "VARCHAR2(100)"
    )
    private String etiquetaCampo;

    // ✅ Indicador para completar con ceros → 0 = No completar, 1 = Completar → Por defecto = 0
    @Column(
            name = "B03_IND_COMPL",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 0"
    )
    private Integer completarCon;

    // ✅ Orden de ejecución → 1 = Izquierda, 2 = Derecha → Por defecto = 1
    @Column(
            name = "B03_ORDEN",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private Integer orden;

    // ✅ Tipo de valor → 0 = Valor directo, 1 = Valor calculado → Por defecto = 0
    @Column(
            name = "B03_TIP_VAL",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 0"
    )
    private Integer tipoValor;

    // ✅ Estado activo → 1 = Activo, 0 = Inactivo → Por defecto = 1
    @Column(
            name = "B03_IND_ACT",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private boolean indActivo;

    // ✅ Fecha de creación → Valor por defecto `SYSDATE`
    @Column(
            name = "B03_FEC_CRE",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT SYSDATE"
    )
    private LocalDateTime fechaCreacion;

    // ✅ Usuario de creación → Valor por defecto `SYSTEM`
    @Column(
            name = "B03_USU_CRE",
            nullable = false,
            updatable = false,
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioCreacion;

    // ✅ Fecha de modificación → Sin valor por defecto
    @Column(
            name = "B03_FEC_MOD",
            columnDefinition = "DATE"
    )
    private LocalDateTime fechaModificacion;

    // ✅ Usuario de modificación → Valor por defecto `SYSTEM`
    @Column(
            name = "B03_USU_MOD",
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
        if (completarCon == null) {
            completarCon = 0;
        }
        if (orden == null) {
            orden = 1;
        }
        if (tipoValor == null) {
            tipoValor = 0;
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
