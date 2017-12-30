import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * Created by the best programmer on 2017/12/30.
 */
public class TestDB {
    public static void main(String[] args) throws IOException{
        try{
            runTest();
        }catch (SQLException ex){
            for(Throwable t:ex){
                t.printStackTrace();
            }
        }
    }
/**
 * runs a test by creating a table,adding a value, showing the table contents, and removing the table.
 */
    public static void runTest() throws SQLException,IOException{
        try(Connection conn=getConnection(); Statement stat=conn.createStatement()){
            stat.executeUpdate("CREATE TABLE GREETINGS(MESSAGE CHAR(20))");
            stat.executeUpdate("INSERT INTO GREETINGS VALUES('hello world')");
            try(ResultSet result=stat.executeQuery("SELECT * FROM Greetings")){
                if(result.next())
                    System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP  TABLE Greetings");
        }
    }

    /**
     * get a connection from the properties specified in the file database.properties.
     *
     * @return the database connection
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        //todo fixbug ：java.nio.file.NoSuchFileException
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}
