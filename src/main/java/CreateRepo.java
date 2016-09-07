import java.io.IOException;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

public class CreateRepo {
	public static void main(String[] arg) throws IOException {
		RepositoryService repo_serv = new RepositoryService(Config.Client);
		Repository new_repo = new Repository();
		new_repo.setName(arg[0]);
		new_repo.setPrivate(true);
		repo_serv.createRepository(Config.Github_Org, new_repo);
	}
}
