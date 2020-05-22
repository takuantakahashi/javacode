import java.util.*;

public class Main {
    public static void main(String[] args){
        // 処理開始
        try{            
            // DBに接続
            Logindao logdao = new Logindao();
            logdao.connectionMethod();
            // 検索開始
            ArrayList<LoginInfo> loginf = logdao.selectMethod();
            // ログイン確認
            if(logdao.loginCheck(loginf)){
                System.out.println("OK");
            }else{
                System.out.println("NG");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("例外発生");
        }
    }
}