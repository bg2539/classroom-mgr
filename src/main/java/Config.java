import java.io.*;
import java.util.*;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.*;

public class Config {
	static final String Github_User = "bg2539";
	static final String Github_Org =  /*"bg2539-github-api-test";*/ "W4118";
	static final String Github_Org_Staging = "W4118-grade";
	static final GitHubClient AdminClient = new GitHubClient().setOAuth2Token("cb56fb889487a7d258e203f1ea0d269fbea9aadf");
	static final String User_DB = System.getProperty("user.home") + "/.github_classroom";

	static final class UserComp implements Comparator<User> {
		public int compare(User a, User b) {
			return a.getLogin().compareTo(b.getLogin());
		}
	}

	static Map<String, String> loadUsers() throws Exception {
		Map<String, String> db = null;
		FileInputStream fis = new FileInputStream(User_DB);
		ObjectInputStream ois = new ObjectInputStream(fis);
		db = (Map<String, String>) ois.readObject();
		ois.close();
		fis.close();
		return db;
	}

	static void StoreUsers(Map<String, String> db) throws Exception {
		FileOutputStream fos = new FileOutputStream(User_DB);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(db);
		oos.close();
		fos.close();
	}
}
