import java.io.IOException;
import java.util.List;

public class RemoveForkRepos {

	public static void main(String[] arg) throws IOException {
		Utils.repo_serv_admin.deleteRepository(Config.Github_Org_Staging, arg[0]);
	}

}
