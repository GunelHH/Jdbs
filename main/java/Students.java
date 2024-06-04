import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.DataLengthException;

import javax.naming.SizeLimitExceededException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {


    private static final String CREATE_TABLE="CREATE TABLE students(id serial primary key,name varchar(20)," +
            "email varchar(20),phoneNumber integer)";
    private static final String INSERT_INTO="INSERT INTO students(name,email,phoneNumber) values('Gunel'," +
            "'gunel@gmail.com',32323232)";
    public static final String SELECT="Select * from students";


    public static void main(String[] args) {

        //Create

//        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres","mysecretpassword")){
//            PreparedStatement prepare=conn.prepareStatement(CREATE_TABLE);
//            prepare.execute();
//
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
        //Insert

//        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres","mysecretpassword")){
//            PreparedStatement prepare=conn.prepareStatement(INSERT_INTO);
//            prepare.execute();
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
        //   Deletee
        Delete(1);
        //select all
        selectStudents();
    }

    private static void selectStudents() {
        try (final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "mysecretpassword")) {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int phoneNumber = resultSet.getInt("phoneNumber");
                System.out.print(id+"\n");
                System.out.print(name+"\n");
                System.out.print(email+"\n");
                System.out.print(phoneNumber+"\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void Delete(int num){
        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","mysecretpassword")){
            final String DELETE="DELETE FROM students WHERE id="+num;
        PreparedStatement prepare=conn.prepareStatement(DELETE);
         prepare.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
