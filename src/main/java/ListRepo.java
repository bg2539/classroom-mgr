import java.io.*;
import java.util.*;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;
import org.eclipse.egit.github.core.service.OrganizationService.*;

public class ListRepo {
  public static void main(String[] arg) throws IOException{
	RepositoryService repo_serv = new RepositoryService(Config.Client);
	CollaboratorService cs_serv = new CollaboratorService(Config.Client);
	OrganizationService org_serv = new OrganizationService(Config.Client);
	Set<User> owners = new TreeSet<User>(new Config.UserComp());
	owners.addAll(org_serv.getMembers(Config.Github_Org, RoleFilter.admin));
	List<Repository> repos = repo_serv.getOrgRepositories(Config.Github_Org);
	System.err.println("Organization Owners are not shown!");
	for(Repository r : repos){
		System.out.println(r.getName());
		List<User> coll = cs_serv.getCollaborators(r);
		for(User u : coll){
			if(!owners.contains(u)){
				System.out.println("\t"+u.getLogin());
			}
		}
	}
  }
}
