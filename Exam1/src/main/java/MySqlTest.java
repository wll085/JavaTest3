import java.sql.*;
import java.util.Scanner;

public class MySqlTest {
    public static final String url="jdbc:mysql://192.168.99.100:3306/sakila";
    public static final String driver="com.mysql.jdbc.Driver";
    public static final String user="root";
    public static final String password="12345";
    public static Connection con=null;
    public static Statement st=null;
    public static ResultSet rs=null;
    public static void Datafromsakilacity(int id){
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,password);
            st=con.createStatement();
            rs=st.executeQuery("SELECT city_id,city FROM city WHERE country_id="+id);
            while(rs.next()){
                System.out.print(rs.getString("city_id")+" ");
                System.out.print(rs.getString("city")+" ");
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void Datafromsakilafilm(int id){
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,password);
            st=con.createStatement();
            rs=st.executeQuery("select f.title \n" +
                    "from film f,inventory i,store s,customer c\n" +
                    "WHERE f.film_id=i.film_id\n" +
                    "and i.store_id=s.store_id\n" +
                    "and s.store_id=c.store_id\n" +
                    "and c.customer_id="+id);
            while(rs.next()){
                System.out.print(rs.getString("title")+" ");
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        int id=0,temp;
        Scanner scanner=new Scanner(System.in);
        System.out.println("查询城市输入1，查询电影输入2");
        temp=scanner.nextInt();
        switch(temp){
            case 1:
                id=scanner.nextInt();
                Datafromsakilacity(id);
                break;
            case 2:
                id=scanner.nextInt();
                Datafromsakilafilm(id);
                break;
            default:
                break;
        }

    }
}
