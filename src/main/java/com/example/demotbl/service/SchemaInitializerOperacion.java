package com.example.demotbl.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class SchemaInitializerOperacion {

    private final DataSource dataSource;

    public SchemaInitializerOperacion(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initSchema() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_ID_ENT IS 'Identificador único de la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_ID_ENT_HOS IS 'Identificador único del host de la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_DES_ENT IS 'Descripción de la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_IND_ACT IS 'Indicador de estado activo (1=Activo, 0=Inactivo)'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_FEC_CRE IS 'Fecha de creación de la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_USU_CRE IS 'Usuario que creó el registro'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_FEC_MOD IS 'Fecha de última modificación de la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE01_ENTIDAD.B01_USU_MOD IS 'Usuario que modificó el registro'");

            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_ID IS 'Identificador único de la operación'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B01_ID_ENT IS 'Referencia a la entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_ID_OPE_HOS IS 'Código de operación único por entidad'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_DES_OPER IS 'Descripción detallada de la operación'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_END_PNT IS 'URL o endpoint asociado a la operación'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_TIP_OPE IS 'Tipo de operación (1=GET, 2=POST, 3=PUT, 4=DELETE). Por defecto = POST'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_TIP_RES IS 'Tipo de respuesta (1=JSON, 2=XML, 3=TEXT). Por defecto = JSON'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_TIM_OUT_CX IS 'Tiempo máximo de conexión en milisegundos'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_NUM_RETRY IS 'Número máximo de reintentos. Por defecto = 3'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_IND_ACT IS 'Indicador de estado activo (1=Activo, 0=Inactivo)'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_FEC_CRE IS 'Fecha de creación'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_USU_CRE IS 'Usuario que creó el registro'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_FEC_MOD IS 'Fecha de última modificación'");
            statement.execute("COMMENT ON COLUMN BNMAIE02_OPERACION.B02_USU_MOD IS 'Usuario que modificó el registro'");

            // ✅ ID generado mediante secuencia personalizada
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_COD_REGLA IS 'Identificador único de la regla'");

            // ✅ Relación con Operacion (Foreign Key)
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_COD_OPER IS 'Código de operación asociada'");

            // ✅ Posición inicial → Obligatorio
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_POS_INICIAL IS 'Posición inicial en la trama'");

            // ✅ Longitud del campo → Por defecto = 0
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_LONG_CAMPO IS 'Longitud del campo (por defecto 0)'");

            // ✅ Nombre del campo → Hasta 100 caracteres
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_NOM_CAMPO IS 'Nombre del campo'");

            // ✅ Etiqueta del campo → Hasta 100 caracteres
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_ETI_CAMPO IS 'Etiqueta del campo'");

            // ✅ Indicador para completar con ceros → 0 = No completar, 1 = Completar → Por defecto = 0
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_IND_COMPL IS 'Indicador para completar con ceros (0=No, 1=Sí)'");

            // ✅ Orden de ejecución → 1 = Izquierda, 2 = Derecha → Por defecto = 1
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_ORDEN IS 'Orden de ejecución (1=Izquierda, 2=Derecha)'");

            // ✅ Tipo de valor → 0 = Valor directo, 1 = Valor calculado → Por defecto = 0
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_TIP_VAL IS 'Tipo de valor (0=Directo, 1=Calculado)'");

            // ✅ Estado activo → 1 = Activo, 0 = Inactivo → Por defecto = 1
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_IND_ACT IS 'Estado de la regla (1=Activo, 0=Inactivo)'");

            // ✅ Fecha de creación → Valor por defecto `SYSDATE`
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_FEC_CRE IS 'Fecha de creación de la regla'");

            // ✅ Usuario de creación → Valor por defecto `SYSTEM`
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_USU_CRE IS 'Usuario que creó el registro'");

            // ✅ Fecha de modificación → Sin valor por defecto
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_FEC_MOD IS 'Fecha de última modificación de la regla'");

            // ✅ Usuario de modificación → Valor por defecto `SYSTEM`
            statement.execute("COMMENT ON COLUMN BNMAIE03_REGLA.B03_USU_MOD IS 'Usuario que modificó el registro'");


            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_ID_PARA IS 'Identificador único del parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_COD_GRUP IS 'Código de grupo'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_COD_PARA IS 'Código único del parámetro dentro del grupo'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_DESCRIP IS 'Descripción del parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_VAL_01 IS 'Primer valor asociado al parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_VAL_02 IS 'Segundo valor asociado al parámetro (opcional)'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_IND_ACT IS 'Estado del parámetro (1=Activo, 0=Inactivo)'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_COD_OPER IS 'Código de la operación asociada al parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_FEC_CRE IS 'Fecha de creación del parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_USU_CRE IS 'Usuario que creó el parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_FEC_MOD IS 'Fecha de última modificación del parámetro'");
            statement.execute("COMMENT ON COLUMN BNMAIE04_PARAMETRO.B04_USU_MOD IS 'Usuario que modificó el parámetro'");


            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_ID IS 'Identificador único del registro de auditoría'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_FEC_LOG IS 'Fecha en que se generó el log (formato DATE)'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_HOR_LOG IS 'Hora exacta del evento (formato HH:mm:ss)'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_CAJERO IS 'Código del cajero que ejecutó la transacción'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_CANAL IS 'Código del canal desde donde se ejecutó la operación'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_APLI IS 'Código de la aplicación emisora del evento'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_TRAN IS 'Código de la transacción ejecutada'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_OPER IS 'Código de la operación realizada'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_ENT IS 'Código de la entidad objetivo de la transacción'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_COD_RETORNO IS 'Código de retorno o estado de la transacción'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_TIP_OPER IS 'Tipo de operación realizada '");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_TXT_DATA IS 'Contenido XML/JSON o datos relevantes del request/response'");
            statement.execute("COMMENT ON COLUMN BNMAIE05_AUDITORIA.F05_FEC_PROCESO IS 'Fecha y hora exacta en que se procesó la operación'");

            System.out.println("✅ Comentarios creados correctamente en Oracle para OPERACION");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creando comentarios en las columnas de OPERACION", e);
        }
    }
}

