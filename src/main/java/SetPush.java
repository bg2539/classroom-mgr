import java.io.*;
import java.util.*;

import org.eclipse.egit.github.core.service.CollaboratorService.PermissionFilter;

public class SetPush {
  public static void main(String[] arg) throws IOException{
	  Utils.SetCollaborator(Config.Github_Org, arg[0], Arrays.copyOfRange(arg, 1, arg.length), PermissionFilter.push);
  }
}
