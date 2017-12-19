package instacarro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;




public class API {
	private static final String URL = " https://api.genderize.io/";
	private static HttpURLConnection conn;
	private static Logger logger = Logger.getLogger("myLogger");
	
	
	public API(){
		
	}
	
	private static String getResponse(String myUrl){
		String json="";
	
		 try {

				URL url = new URL(myUrl);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				while ((output = br.readLine()) != null) {
					logger.info("Output from Server .... \n" +output);
					json = json+output;
				}		
		
			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();
			  }
		 
		 return json;
	}
	
	
	
	public static void disconnect(){
		if(conn != null) conn.disconnect();		
	}
	
	public static Logger getLogger(){
		return logger;
	}
	
	public static Genderize[] getMultipleRequests(String myUrl){
		String json = getResponse(myUrl);
		Gson mapper = new Gson();
		Genderize[] gen= mapper.fromJson(json, Genderize[].class);
		return gen;
	}
	
	public static Genderize getSingleRequest(String myUrl){
		String json = getResponse(myUrl);
		Gson mapper = new Gson();
		Genderize gen= mapper.fromJson(json, Genderize.class);
		return gen;
	}
	
	
	//******************************************//
	//  Methods to append params to base URL	//
	//******************************************//
	
	
	public static String appendName(String name){
		return URL+"?name="+name;
	}
	
	public static String appendNames(String ... names){
		String result="?";
		for(int i=0; i<names.length; i++){
			result=result+"name["+i+"]="+names[i]+"&";
		}
		return URL+result;
	}
	
	public static String appendNameCountry(String name, String country){
		return appendName(name)+"&country_id="+country;
	}
	
	public static String appendNameLanguage(String name, String language){
		return appendName(name)+"&language_id="+language;
	}
	
	
}

