import java.io.*;

public class RemoveRepo {
  public static void main(String[] arg) throws IOException{
	Utils.repo_serv_admin.deleteRepository(Config.Github_Org, arg[0]);
  }
}
