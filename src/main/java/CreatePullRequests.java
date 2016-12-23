import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.CollaboratorService.PermissionFilter;

public class CreatePullRequests {

	public static void main(String[] args) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(args[1]));
		String message = new String(encoded, Charset.defaultCharset());
		List<Repository> repos = Utils.GetRepos(Config.Github_Org, args[0]);
		Set<User> members = Utils.GetMembers(Config.Github_Org);
		for(Repository r : repos){
			List<User> users = Utils.cs_serv_admin.getCollaborators(r);
			Repository r_fork;
			try {
				r_fork = Utils.repo_serv_admin.getRepository(Config.Github_Org_Staging, r.getName());
			}
			catch (RequestException e) {
				e.printStackTrace();
				System.err.println("Forked Repo for " + r.getName() + " Does not exist!!");
				continue;
			}
			for(User u : users){
				if(!members.contains(u)){
					//Allow students to pull grading repo
					System.err.println("Allowing " + u.getLogin() + " to pull grading repo " + r_fork.getHtmlUrl());
					Utils.cs_serv_admin.addCollaborator(r_fork, u.getLogin(), PermissionFilter.pull);
				}
			}
			PullRequest pr = new PullRequest();
			pr.setTitle(args[1]);
			pr.setBody(message);
			PullRequestMarker mark_base = new PullRequestMarker().setLabel("master");
			pr.setBase(mark_base);
			PullRequestMarker mark_head = new PullRequestMarker().setLabel(Config.Github_Org_Staging + ":master");
			pr.setHead(mark_head);
			System.err.println("creating pull request for " + r.getName());
			try{
				Utils.pullreq_serv_admin.createPullRequest(r, pr);
			}
			catch(RequestException e){
				e.printStackTrace();
			}
		}
	}

}
