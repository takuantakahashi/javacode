import java.sql.*;
import java.util.*;

public class Logindao {
    Connection con = null;
    LoginInfo loginInfo;
    String loginUser;
    String loginPass;
    Scanner scan;
    // 接続用メソッド
    public Connection connectionMethod()throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // 接続情報を入力
            scan = new Scanner(System.in);
            System.out.print("MySQLのホスト名を入力(例:localhost:3306) : ");
            String hostName = scan.nextLine();
            System.out.print("MySQLのDB名を入力(例:sampledb : )");
            String dbName = scan.nextLine();
            String url = "jdbc:mysql://" + hostName + "/" + dbName; // MySQLのURLを指定
            System.out.print("MySQLのユーザー名を入力 : ");
            String user = scan.nextLine(); //MySQLユーザー名
            System.out.print("MySQLのパスワードを入力 : ");
            String dbPass = scan.nextLine(); //MySQLパスワード

            // DBに接続
            con = DriverManager.getConnection(url, user, dbPass);
            System.out.println("server名：" + hostName);
            System.out.println("接続DB名：" + dbName);
        }catch(ClassNotFoundException e){
            System.out.println("クラスパスが指定されていない or ドライバーがない");
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("接続(コネクション)に失敗");
            e.printStackTrace();
        }
        return con;
    }

    //　データ取得用メソッド
    public ArrayList<LoginInfo> selectMethod(){
        try{
            System.out.println("ログインする名前を指定");
            this.loginUser = scan.nextLine();
            System.out.println("ログインパスワードを入力");
            this.loginPass = scan.nextLine();
            String sql = "SELECT * FROM sample WHERE NAME=? && PASSWORD=?";
            PreparedStatement pstm = con.prepareStatement(sql);
             pstm.setString(1, loginUser);
             pstm.setString(2, loginPass);
            ResultSet rs = pstm.executeQuery();

            // 取得したログインユーザー名を格納
            ArrayList<LoginInfo> logindtos = new ArrayList<>();
            while(rs.next()){
                loginInfo = new LoginInfo();
                loginInfo.setName(rs.getString("NAME"));
                loginInfo.setPass(rs.getString("PASSWORD"));
                logindtos.add(loginInfo);
            }
            rs.close();
            pstm.close();
            scan.close();
            return logindtos;
        }catch(SQLException e){
            System.out.println("取得に失敗");
            e.printStackTrace();
            return null;
        }finally{
            if(con != null){
                try{
                    con.close();
                }catch(SQLException e){}
            }
        }
    }

    // 確認用メソッド
    public boolean loginCheck(ArrayList<LoginInfo> logInf){
        for(LoginInfo lf:logInf){
            String loginName = lf.getName();
            String loginPass = lf.getPassword();
            if(loginName.equals(this.loginUser)&&loginPass.equals(this.loginPass)){
                return true;
            }
        }
        return false;
    }
}