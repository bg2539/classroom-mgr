import java.io.*;

import org.eclipse.egit.github.core.service.*;

public class RemoveRepo {
  public static void main(String[] arg) throws IOException{
	RepositoryService repo_serv = new RepositoryService(Config.Client);
	repo_serv.deleteRepository(Config.Github_Org, arg[0]);
  }
}
