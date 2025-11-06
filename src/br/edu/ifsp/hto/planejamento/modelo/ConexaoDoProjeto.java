package br.edu.ifsp.hto.planejamento.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * classe em que as informações de conexão estarão guardadas!
 * 
 * TODO: implementar um arquivo que guarde essas informações!
 */
public class ConexaoDoProjeto {
    public static final String URL = "jdbc:postgresql://localhost:3306/planejamento_producao";
    public static final String USER = "postgres";
    public static final String PSWD = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PSWD);
    }
}
