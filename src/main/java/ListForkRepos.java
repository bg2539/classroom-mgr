import java.io.IOException;

public class ListForkRepos {

	public static void main(String[] arg) throws IOException {
		ListRepos.printRepos(arg, Config.Github_Org_Staging);
	}
}
