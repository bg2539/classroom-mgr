import java.io.IOException;
import java.util.Arrays;

import org.eclipse.egit.github.core.service.CollaboratorService.PermissionFilter;

public class SetPull {

	  public static void main(String[] arg) throws IOException{
		  Utils.SetCollaborator(Config.Github_Org, arg[0], Arrays.copyOfRange(arg, 1, arg.length), PermissionFilter.pull);
	  }

}
