import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.*;

public class RemoveTeam {
	public static void main(String[] arg) throws IOException {
		List<Team> teams = Utils.team_serv_admin.getTeams(Config.Github_Org);
		for(Team team : teams){
			if(team.getName().equals(arg[0])){
				Utils.team_serv_admin.deleteTeam(team.getId());
				break;
			}
		}
	}
}
