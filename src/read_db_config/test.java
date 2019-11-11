package read_db_config;

import java.util.HashMap;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> cred = ReadDBConfig.getCredentials("/home/cash/NewFile.xml");
		if (cred.get("success").equals("true")) {
			System.out.println(cred.get("hostname"));
			System.out.println(cred.get("port"));
			System.out.println(cred.get("dbname"));
			System.out.println(cred.get("password"));
		} else {			
			System.out.println(cred.get("message"));
		}
	}

}
