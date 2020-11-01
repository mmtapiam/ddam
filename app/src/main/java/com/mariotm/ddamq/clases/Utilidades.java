package com.mariotm.ddamq.clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mariotm.ddamq.clases.vo.AvatarVo;
import com.mariotm.ddamq.clases.vo.CategoriaVo;

import java.util.ArrayList;

public class Utilidades {

    public static ArrayList<CategoriaVo> listaCategorias = null;

    public static void obtenerListaCategorias() {

        listaCategorias = new ArrayList<CategoriaVo>();

    }

    public static final String BASE_DATOS = "sistemapos";

    //Constantes de la Tabla Usuarios
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PERFIL = "perfil";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_CONTRASEÑA = "contraseña";

    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIOS+" ("
            +CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT, "
            +CAMPO_PERFIL+" TEXT, "+CAMPO_USUARIO+" TEXT, "+CAMPO_CONTRASEÑA+" TEXT)";

    //Constantes de la Tabla Categorias
    public static final String TABLA_CATEGORIAS = "categorias";
    public static final String CAMPO_ID_CATEGORIA = "id";
    public static final String CAMPO_CATEGORIA = "categoria";
    public static final String CAMPO_DESCRIPCION = "descripcion";

    public static final String CREAR_TABLA_CATEGORIAS = "CREATE TABLE "+TABLA_CATEGORIAS+" ("
            +CAMPO_ID_CATEGORIA+" INTEGER PRIMARY KEY, "+CAMPO_CATEGORIA+" TEXT, "
            +CAMPO_DESCRIPCION+" TEXT)";

    //Constantes de la Tabla Productos
    public static final String TABLA_PRODUCTOS = "productos";

    //Constantes de la Tabla Clientes
    public static final String TABLA_CLIENTES = "clientes";

    //Constantes de la Tabla Ventas
    public static final String TABLA_VENTAS = "ventas";


    // Consultar lista de Categorias
    public static void consultarListaCategorias(Activity activity) {
        ConexionSQLite conectar = new ConexionSQLite(activity, BASE_DATOS, null, 1);
        SQLiteDatabase db = conectar.getReadableDatabase();
        CategoriaVo categoria = null;
        listaCategorias = new ArrayList<CategoriaVo>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_CATEGORIAS, null);

        while (cursor.moveToNext()) {
            categoria = new CategoriaVo();
            categoria.setId(cursor.getInt(0));
            categoria.setCategoria(cursor.getString(1));
            categoria.setDescripcion(cursor.getString(2));

            listaCategorias.add(categoria);
        }
        db.close();
    }

}
