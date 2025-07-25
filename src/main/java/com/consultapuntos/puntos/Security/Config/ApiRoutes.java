package com.consultapuntos.puntos.Security.Config;

public class ApiRoutes {

    public static final String BASE = "/api/v2/puntos";

    // === AUTH ===
    public static final String AUTH = BASE + "/auth/login";

    // === USERS ===
    public static final String BASE_USER = BASE + "/users";
    public static final String USERS_CREATE = BASE_USER + "/create";
    public static final String USERS_CHANGE_PASSWORD = BASE_USER + "/change-passwd/{codeuser}";
    public static final String USERS_INACTIVATE = BASE_USER + "/inactivate/{codeuser}";
    public static final String USERS_ACTIVATE = BASE_USER + "/activate/{codeuser}";
    public static final String USERS_RESET_PASSWORD = BASE_USER + "/reset-password/{codeuser}";
    public static final String USERS_MY_SESSION = BASE_USER + "/mysession";
    public static final String USERS_UPDATE_ROLE = BASE_USER + "/update-role";
    public static final String USERS_LIST = BASE_USER + "/listUsers";

    // === PUNTOS ===
    public static final String PUNTOS_CREATE = BASE + "/create";
    public static final String PUNTOS_DELETE = BASE + "/delete/{codigo}";
    public static final String PUNTOS_UPDATE = BASE + "/update/{codigo}";
    public static final String PUNTOS_REPORTS = BASE + "/reports";
    public static final String PUNTOS_REPORTS_FORMAT_ANSIBLE = BASE + "/reports-FormatAnsible";
    public static final String PUNTOS_LIST = BASE + "/list";
    public static final String PUNTOS_FIND_BY_CODIGO = BASE + "/findByCodigo/{codigo}";
    public static final String PUNTOS_FIND_BY_NOMBRE = BASE + "/findByNombre/{nombre}";
    public static final String PUNTOS_FIND_BY_IP = BASE + "/findByIp/{ip}";
    public static final String PUNTOS_FIND_BY_CODIGO_AS_TEXT = BASE + "/findByCodigoAsText/{texto}";
    public static final String PUNTOS_IMPORT_EXCEL = BASE + "/upload-excel";

    // === TIPO CONEXIONES ===
    public static final String BASE_TIPO_CONEXION = BASE + "/tipo-conexiones";
    public static final String TIPO_CONEXION_FIND_BY_CODE = BASE_TIPO_CONEXION + "/findTypeConnectionBycode/{code}";
    public static final String TIPO_CONEXION_GET_ALL = BASE_TIPO_CONEXION + "/getAllConnection";
    public static final String TIPO_CONEXION_UPDATE = BASE_TIPO_CONEXION + "/updateTypeConnectionByCode/{code}";
    public static final String TIPO_CONEXION_CREATE = BASE_TIPO_CONEXION + "/createTypeConnection";

    // === ZONAS ===
    public static final String BASE_ZONA = BASE + "/zonas";
    public static final String ZONAS_FIND_BY_CODE = BASE_ZONA + "/findByCode/{code}";
    public static final String ZONAS_GET_ALL = BASE_ZONA + "/getAll";
    public static final String ZONAS_UPDATE_NAME = BASE_ZONA + "/updateName/{code}";
    public static final String ZONAS_CREATE = BASE_ZONA + "/create";

    // === CENTROS DE COSTO ===
    public static final String BASE_CCOSTO = BASE + "/centros-costos";
    public static final String CCOSTO_FIND_BY_CODE = BASE_CCOSTO + "/findByCode/{code}";
    public static final String CCOSTO_LIST = BASE_CCOSTO + "/list";
    public static final String CCOSTO_UPDATE_NAME = BASE_CCOSTO + "/updateName/{code}";
    public static final String CCOSTO_CREATE = BASE_CCOSTO + "/create";

}
