package exec;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by the best programmer on 2017/12/30.
 */
public class ExecSQL {
    /**
     * Executes all SQL statements in a file. Call this program as <br>
     *     java -classpath driverPath:. ExecSQL commandFile
     */
    public static void main(String[] args) throws IOException{
        try(Scanner in=args.length==0?new Scanner(System.in):new Scanner(Paths.get(args[0]),"UTF-8")){
            try(Connection conn=getConnection(); Statement stat=conn.createStatement()){
                while (true){
                    if(args.length==0)System.out.println("Enter command or EXIT to exit:");
                    if(!in.hasNextLine())return;
                    String line=in.nextLine().trim();
                    if(line.equalsIgnoreCase("EXIT"))return;
                    if(line.endsWith(":")){//reomve trailing semicolon
                        line=line.substring(0,line.length()-1);
                    }
                }
            }
        }catch (SQLException e){

        }
    }

    public static Connection getConnection() throws SQLException,IOException{
        Properties props = new Properties();
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
