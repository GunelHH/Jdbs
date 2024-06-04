import java.sql.*;

public class Users {


    private static final String CREATE_TABLE="CREATE TABLE users(id serial primary key,name varchar(20)," +
            "email varchar(20),phoneNumber integer)";
    private static final String INSERT_INTO="INSERT INTO users(name,email,phoneNumber) values(?,?,?)";
    public static final String SELECT="Select * from users";


    public static void main(String[] args) {

//       Create();
       Insert();
       Delete(1);
       selectUsers();
       Update(2);
       SelectById(3);
    }
    private static void Create() {
        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","mysecretpassword")){
            PreparedStatement prepare=conn.prepareStatement(CREATE_TABLE);
            prepare.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void Insert() {
        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","mysecretpassword")){
            PreparedStatement prepare=conn.prepareStatement(INSERT_INTO);
            prepare.setString(1,"Gunel");
            prepare.setString(2,"test@gmail.com");
            prepare.setInt(3,343434324);
            prepare.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void selectUsers() {
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
            final String DELETE="DELETE FROM users WHERE id="+num;
        PreparedStatement prepare=conn.prepareStatement(DELETE);
         prepare.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private static void Update(int num){
        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","mysecretpassword")){
            final String UPDATE="UPDATE users SET name='Jeyhuna' WHERE id="+num;
            PreparedStatement prepare=conn.prepareStatement(UPDATE);
            prepare.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private static void SelectById(int id){
        try(final Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","mysecretpassword")){
            final String select="Select * from users where id="+id;
            PreparedStatement prepare=conn.prepareStatement(select);
            prepare.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
