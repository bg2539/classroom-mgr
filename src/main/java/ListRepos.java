import java.io.*;

public class ListRepos {
	
	public static void printRepos(String[] arg, String org) throws IOException{
		boolean verbose = false;
		String prefix = null;
		int idx = 0;
		if(arg.length > idx && arg[0].equals("-v")){
			verbose = true;
			++idx;
		}
		if(arg.length > idx){
			prefix = arg[idx];
		}
		Utils.PrintRepos(org, prefix, verbose);
	}
	
	public static void main(String[] arg) throws IOException {
		printRepos(arg, Config.Github_Org);
	}
}
