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

            System.out.println("✅ Comentarios creados correctamente en Oracle para OPERACION");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creando comentarios en las columnas de OPERACION", e);
        }
    }
}

