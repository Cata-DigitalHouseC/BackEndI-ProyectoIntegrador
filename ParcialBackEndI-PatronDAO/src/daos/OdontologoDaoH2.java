package daos;

import entidades.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo>{

    //Query SQL para crear la tabla
    String queryCreate="CREATE TABLE odontologos (nroMatricula varchar(25),nombre varchar(255), apellido varchar(255));";

    //Variables para configurar la conexion
    private final static String DB_JDBC_DRIVER="org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_clinicaOdontologicaBE";
    private final static String DB_USER="sa";
    private final static String DB_PASSWORD= "sa";

    private final static String DB_OTHERS="INIT=RUNSCRIPT FROM 'createTableOdontologos.sql'";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try{
            //1 Levantar el driver y los conectores
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinicaOdontologicaBE;PASSWORD=sa;USER=sa;INIT=RUNSCRIPT FROM 'createTableOdontologos.sql'");

            //2 Crear una sentencia
            String queryInsert = "INSERT INTO odontologos VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(queryInsert);
            preparedStatement.setString(1,odontologo.getNroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            //3 Ejecutar la sentencia
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            //1 Levantar el driver y los conectores
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //2 Crear una sentencia
            String queryBuscarTodos = "SELECT * FROM odontologos";
            preparedStatement = connection.prepareStatement(queryBuscarTodos);

            //3 Ejecutar la sentencia
            ResultSet resultSet = preparedStatement.executeQuery();

            //Extraer los resultados
            while(resultSet.next()){
                String nroMatricula = resultSet.getString("nroMatricula");
                String nombre = resultSet.getString("nombre");
                String apellido= resultSet.getString("apellido");

                Odontologo odontologo = new Odontologo(nroMatricula,nombre,apellido);

                odontologos.add(odontologo);

            }
            preparedStatement.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odontologos;
    }
}
