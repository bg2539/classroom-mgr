import java.util.*;

public class UpdateUserDb {
	public static void main(String[] arg) throws Exception{
		Map<String, String> db = null;
		try{
			db = Config.loadUsers();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(db == null){
			db = new TreeMap<String, String>();
		}
		if(arg[0].equals("-")){
			System.err.println("Removing " + arg[1]);
			db.remove(arg[1]);
		}
		else{
			if(db.containsKey(arg[0])){
				System.err.println("Replacing " + arg[0] + " !");
			}
			db.put(arg[0], arg[1]);
		}
		Config.StoreUsers(db);
		for(Map.Entry<String, String> e : db.entrySet()){
			System.out.println(e.getKey() + " -> " + e.getValue());
		}
	}
}
