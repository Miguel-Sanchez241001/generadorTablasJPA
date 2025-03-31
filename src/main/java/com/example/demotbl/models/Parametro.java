package com.example.demotbl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "BNMAIE04_PARAMETRO",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_B04_COD_GRUP_PARA",
                        columnNames = {"B04_COD_GRUP", "B04_COD_PARA"}
                )
        }
)
@SequenceGenerator(
        name = "BNSQ_04_PARAMETRO",
        sequenceName = "BNSQ_04_PARAMETRO",
        allocationSize = 1
)
public class Parametro {

    // ✅ ID generado mediante secuencia personalizada
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BNSQ_04_PARAMETRO")
    @Column(
            name = "B04_ID_PARA",
            nullable = false,
            updatable = false,
            columnDefinition = "NUMBER(10)"
    )
    private Long id;

    // ✅ Código de grupo → Puede repetirse
    @Column(
            name = "B04_COD_GRUP",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String codigoGrupo;

    // ✅ Código de parámetro → No puede repetirse dentro de un mismo grupo
    @Column(
            name = "B04_COD_PARA",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String codigoParam;

    // ✅ Descripción del parámetro → Hasta 150 caracteres
    @Column(
            name = "B04_DESCRIP",
            nullable = false,
            length = 150,
            columnDefinition = "VARCHAR2(150)"
    )
    private String descripcion;

    // ✅ Valor 1 → No puede ser nulo
    @Column(
            name = "B04_VAL_01",
            nullable = false,
            length = 255,
            columnDefinition = "VARCHAR2(255)"
    )
    private String valor1;

    // ✅ Valor 2 → Puede ser nulo
    @Column(
            name = "B04_VAL_02",
            length = 255,
            columnDefinition = "VARCHAR2(255)"
    )
    private String valor2;

    // ✅ Estado activo → 1 = Activo, 0 = Inactivo → Por defecto = 1
    @Column(
            name = "B04_IND_ACT",
            nullable = false,
            columnDefinition = "NUMBER(1) DEFAULT 1"
    )
    private Boolean activo;

    // ✅ Relación con Operacion (opcional)
    @ManyToOne
    @JoinColumn(
            name = "B04_COD_OPER",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_B04_COD_OPER") // Nombre específico de la FK
    )
    private Operacion operacion;

    // ✅ Fecha de creación → Valor por defecto SYSDATE
    @Column(
            name = "B04_FEC_CRE",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE DEFAULT SYSDATE"
    )
    private LocalDateTime fechaCreacion;

    // ✅ Usuario de creación → Valor por defecto SYSTEM
    @Column(
            name = "B04_USU_CRE",
            nullable = false,
            updatable = false,
            length = 50,
            columnDefinition = "VARCHAR2(50) DEFAULT 'SYSTEM'"
    )
    private String usuarioCreacion;

    // ✅ Fecha de modificación → Sin valor por defecto
    @Column(
            name = "B04_FEC_MOD",
            columnDefinition = "DATE"
    )
    private LocalDateTime fechaModificacion;

    // ✅ Usuario de modificación → Valor por defecto SYSTEM
    @Column(
            name = "B04_USU_MOD",
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
        if (activo == null) {
            activo = true;
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
