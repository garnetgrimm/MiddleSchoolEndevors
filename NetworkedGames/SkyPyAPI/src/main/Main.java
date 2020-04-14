package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class Main {
	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httppost = new HttpGet("http://localhost:5000/updateJava");

		//Execute and get the response.
		while(true) {
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String s = readStream(instream);
					//"illegal repitition" what, also NEED TO FIX THE CURRUSER GLITCH
					String[] tokens = s.substring(1, s.length() - 1).split("\\[SBSPL\\]");
					for(int i = 0; i < tokens.length; i++) {
						if(!tokens[i].equals("")) {
							System.out.println(tokens[i].substring(0, tokens[i].length() - 1));	
						}
					}
					
					//System.out.println(s);
				} finally {
					instream.close();
				}
			}
			 Thread.sleep(2000); 
		}
	}
	
	private static String readStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
		  
	}
}