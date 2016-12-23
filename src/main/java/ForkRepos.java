import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.*;

public class ForkRepos {

	public static void main(String[] args) throws IOException {
		List<Repository> repos = Utils.GetRepos(Config.Github_Org, args[0]);
		for(Repository r : repos){
			System.err.println("forking " + r.getName());
			Utils.repo_serv_admin.forkRepository(r, Config.Github_Org_Staging);
		}
	}

}
