import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;
import org.eclipse.egit.github.core.service.CollaboratorService.PermissionFilter;
import org.eclipse.egit.github.core.service.OrganizationService.RoleFilter;

public class Utils {
	static RepositoryService repo_serv_admin = new RepositoryService(Config.AdminClient);
	static CollaboratorService cs_serv_admin = new CollaboratorService(Config.AdminClient);
	static OrganizationService org_serv_admin = new OrganizationService(Config.AdminClient);
	static TeamService team_serv_admin = new TeamService(Config.AdminClient);
	static MarkdownService md_serv_admin = new MarkdownService(Config.AdminClient);
	static PullRequestService pullreq_serv_admin = new PullRequestService(Config.AdminClient);
	
	public static Set<User> GetMembers(String org) throws IOException{
		Set<User> owners = new TreeSet<User>(new Config.UserComp());
		owners.addAll(org_serv_admin.getMembers(org, RoleFilter.all));
		return owners;
	}
	
	public static List<Repository> GetRepos(String _org, String _prefix) throws IOException{
		List<Repository> repos = repo_serv_admin.getOrgRepositories(_org);
		Iterator<Repository> i = repos.iterator();
		while(i.hasNext()){
			Repository r = i.next();
			if(_prefix != null && !r.getName().startsWith(_prefix)){
				i.remove();
			}
		}
		return repos;
	}
	
	public static void SetCollaborator(String _org, String _repo, String[] _users, PermissionFilter _perm) throws IOException {
		Repository repo = repo_serv_admin.getRepository(_org, _repo);
		List<User> users = cs_serv_admin.getCollaborators(repo);
		Set<User> members = GetMembers(_org);
		for (User u : users) {
			if (!members.contains(u)) {
				cs_serv_admin.removeCollaborator(repo, u.getLogin());
			}
		}
		for (int i = 0; i != _users.length; ++i) {
			System.err.println("Adding " + _users[i] + " as (" + _perm.toString() + ") to repo " + _org + "/" + _repo);
			cs_serv_admin.addCollaborator(repo, _users[i], _perm);
		}
	}

	public static void LockRepos(String _org, String _prefix) throws IOException {
		List<Repository> repos = GetRepos(_org, _prefix);
		Set<User> members = GetMembers(_org);
		for(Repository repo : repos){
			List<User> users = cs_serv_admin.getCollaborators(repo);

			for (User u : users) {
				if (!members.contains(u)) {
					cs_serv_admin.removeCollaborator(repo, u.getLogin());
					cs_serv_admin.addCollaborator(repo, u.getLogin(), PermissionFilter.pull);
				}
			}
		}
	}
	public static void PrintRepos(String _org, String _prefix, boolean verbose) throws IOException {
		Set<User> members = GetMembers(_org);
		List<Repository> repos = GetRepos(_org, _prefix);
		System.err.println("Organization Members are not shown!");
		for (Repository r : repos) {
			if(verbose){
				System.out.print("{" + r.getCreatedAt() + "} ");
			}
			System.out.print(r.getName());
			List<User> coll = Utils.cs_serv_admin.getCollaborators(r);
			for (User u : coll) {
				if (!members.contains(u)) {
					System.out.print(" ");
					if(verbose){
						System.out.print("[");
						if(u.permissions.admin)
							System.out.print("Admin");
						if(u.permissions.push)
							System.out.print("Push");
						if(u.permissions.pull)
							System.out.print("Pull");
						System.out.print("]");
					}
					System.out.print(u.getLogin());
				}
			}
			System.out.println();
		}
	}
	
}
