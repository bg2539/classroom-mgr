import java.io.IOException;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

public class AddTeam {
	public static void main(String[] arg) throws IOException {
		TeamService team_serv = new TeamService(Config.AdminClient);
		Team team = new Team();
		team.setName(arg[0]);
		team.setPermission("pull");
		team = team_serv.createTeam(Config.Github_Org, team);
		for(int i = 1; i!= arg.length; ++i){
			team_serv.addMembership(team.getId(), arg[i]);
		}
	}
}
