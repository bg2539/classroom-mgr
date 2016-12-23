import java.io.IOException;

import org.eclipse.egit.github.core.*;

public class CreateRepo {
	public static void main(String[] arg) throws IOException {
		Repository new_repo = new Repository();
		new_repo.setName(arg[0]);
		new_repo.setPrivate(true);
		Utils.repo_serv_admin.createRepository(Config.Github_Org, new_repo);
	}
}
