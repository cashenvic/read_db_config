package read_db_config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadDBConfig {
	
	/**
	 * Reads the XML config file which path is given in parameter and returns it as a HashMap
	 * with <b>hostname</b>, <b>port</b>, <b>dbname</b>, <b>username</b>, <b>password</b>.
	 * A success status is available and a message to describe it
	 * <br><br>Usage Example: <br>HashMap cred = ReadDBConfig.getCredentials("/chemin/fichier.xml");<br>
	 * String usr = cred.get("username");<br>
	 * String pwd = cred.get("password");<br>
	 * 
	 * @param configPath Absolute path of the xml config file
	 * @return Credentials as HashMap with the following members:
	 * <ul>
	 * 	<li>hostname</li>
	 * 	<li>port</li>
	 * 	<li>dbname</li>
	 * 	<li>username</li>
	 * 	<li>password</li>
	 * </ul>
	 */
    public static HashMap<String, String> getCredentials(String configPath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        HashMap<String, String> credentials = new HashMap<String, String>();
        
        try {
            builder = factory.newDocumentBuilder();
            File fileXML = new File(configPath);

            Document document = builder.parse(fileXML);
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            String hostname = (String) xpath.compile("//config//jdbc//serveur").evaluate(document, XPathConstants.STRING);
            String port = (String) xpath.compile("//config//jdbc//port").evaluate(document, XPathConstants.STRING);
            String dbname = (String) xpath.compile("//config//jdbc//db").evaluate(document, XPathConstants.STRING);
            String identidiant = (String) xpath.compile("//config//jdbc//username").evaluate(document, XPathConstants.STRING);
            String mot_de_passe = (String) xpath.compile("//config//jdbc//password").evaluate(document, XPathConstants.STRING);

            credentials.put("success", "true");
            credentials.put("hostname", hostname);
            credentials.put("port", port);
            credentials.put("dbname", dbname);
            credentials.put("username", identidiant);
            credentials.put("password", mot_de_passe);
            
        } catch (ParserConfigurationException ex) {
        	credentials.put("success", "false");
        	credentials.put("message", ex.toString());
        } catch (SAXException ex) {
        	credentials.put("success", "false");
        	credentials.put("message", ex.toString());
        } catch (IOException ex) {
        	credentials.put("success", "false");
        	credentials.put("message", ex.toString());
        } catch (XPathExpressionException ex) {
        	credentials.put("success", "false");
        	credentials.put("message", ex.toString());
        }
              
        return credentials;
    }

}
