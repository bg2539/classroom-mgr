import java.util.*;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

public class UserDetail {
	public static void main(String[] arg) throws Exception {
		UserService user_serv = new UserService(Config.AdminClient);
		Map<String, String> db = Config.loadUsers();
		if (arg.length != 0) {
			String u = db.get(arg[0]);
			try {
				if(u == null)
					System.exit(1);
				User user = user_serv.getUser(db.get(arg[0]));
				System.out.println(user.getLogin());
			} catch (Exception e) {
				System.out.println(u);
				System.exit(2);
			}
			return;
		}
		for (Map.Entry<String, String> en : db.entrySet()) {
			try {
				System.out.print(en.getKey() + " " + en.getValue() + " ");
				User user = user_serv.getUser(en.getValue());
				System.out.println(user.getHtmlUrl() + " " + user.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
