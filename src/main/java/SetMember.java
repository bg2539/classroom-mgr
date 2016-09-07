import java.io.*;
import java.util.*;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;
import org.eclipse.egit.github.core.service.OrganizationService.RoleFilter;

public class SetMember {
  public static void main(String[] arg) throws IOException{
	RepositoryService repo_serv = new RepositoryService(Config.Client);
	CollaboratorService cs_serv = new CollaboratorService(Config.Client);
	OrganizationService org_serv = new OrganizationService(Config.Client);
	Repository repo = repo_serv.getRepository(Config.Github_Org, arg[0]);
	List<User> users = cs_serv.getCollaborators(repo);
	Set<User> owners = new TreeSet<User>(new Config.UserComp());
	owners.addAll(org_serv.getMembers(Config.Github_Org, RoleFilter.admin));
	for(User u : users){
		if(!owners.contains(u)){
			cs_serv.removeCollaborator(repo, u.getLogin());
		}
	}
	for(int i=1;i!=arg.length;++i){
		cs_serv.addCollaborator(repo, arg[i]);
	}
  }
}
