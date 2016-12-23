import java.io.*;

public class LockRepos {
	  public static void main(String[] arg) throws IOException{
		  Utils.LockRepos(Config.Github_Org, arg[0]);
	  }
}
