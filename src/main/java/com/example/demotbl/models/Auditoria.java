package com.example.demotbl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "BNMAIE05_AUDITORIA")
@SequenceGenerator(
        name = "BNSQ_05_AUDITORIA",
        sequenceName = "BNSQ_05_AUDITORIA",
        allocationSize = 1
)
public class Auditoria {

    // ✅ Identificador autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BNSQ_05_AUDITORIA")
    @Column(
            name = "F05_ID",
            nullable = false,
            updatable = false,
            columnDefinition = "NUMBER(10)"
    )
    private Long id;

    // ✅ Fecha en formato DATE
    @Column(
            name = "F05_FEC_LOG",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate fecha;

    // ✅ Hora exacta (formato: HH:mm:ss)
    @Column(
            name = "F05_HOR_LOG",
            nullable = false,
            columnDefinition = "VARCHAR2(8)"
    )
    private String hora;

    // ✅ Código de cajero
    @Column(
            name = "F05_COD_CAJERO",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String cajero;

    // ✅ Canal de origen
    @Column(
            name = "F05_COD_CANAL",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String canal;

    // ✅ Aplicación emisora
    @Column(
            name = "F05_COD_APLI",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String aplicacion;

    // ✅ Código de transacción
    @Column(
            name = "F05_COD_TRAN",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String transaccion;

    // ✅ Código de operación
    @Column(
            name = "F05_COD_OPER",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String operacion;

    // ✅ Código de entidad
    @Column(
            name = "F05_COD_ENT",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String entidad;

    // ✅ Código de retorno de la operación
    @Column(
            name = "F05_COD_RETORNO",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String retorno;

    // ✅ Tipo de operación
    @Column(
            name = "F05_TIP_OPER",
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR2(10)"
    )
    private String tipoOperacion;

    // ✅ Payload o cuerpo del request/response
    @Lob
    @Column(
            name = "F05_TXT_DATA",
            columnDefinition = "CLOB"
    )
    private String data;

    // ✅ Fecha y hora completa del procesamiento
    @Column(
            name = "F05_FEC_PROCESO",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDateTime fechaProceso;
}
