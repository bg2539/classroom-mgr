import java.util.Comparator;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.*;

public class Config {
	static final String Github_User = "bg2539";
	static final String Github_Org = "W4118";
	static final GitHubClient Client = new GitHubClient().
			setOAuth2Token("6f2ae2fb3c780d230e2876ea15f0fb6058b163ba");
	static final class UserComp implements Comparator<User>{
		public int compare(User a, User b){
			return a.getLogin().compareTo(b.getLogin());
		}
	}
}
