import java.io.*;

import org.eclipse.egit.github.core.*;

public class RemoveMember {
  public static void main(String[] arg) throws IOException{
	Repository repo = Utils.repo_serv_admin.getRepository(Config.Github_Org, arg[0]);
	for(int i=1;i!=arg.length;++i){
		Utils.cs_serv_admin.removeCollaborator(repo, arg[i]);
	}
  }
}
