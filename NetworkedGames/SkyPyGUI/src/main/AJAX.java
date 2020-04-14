package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import States.ConvertState;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class AJAX implements Runnable {
	public static void Start() {
		
		try {
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
					if(!s.equals("\"\"")) System.out.println(s);
					String[] tokens = s.substring(1, s.length() - 1).split("\\[SBSPL\\]");
					for(int i = 0; i < tokens.length; i++) {
						if(!tokens[i].equals("")) {
							String name = tokens[i].split("\\[BSPL\\]")[0];
							String[] c = tokens[i].substring(name.length() + 6, tokens[i].length()).split("\\[SPL\\]");
							Badge b = new Badge(name, c[0], c[1], c[2], c[3], c[4]);
							ConvertState.badges.add(b);
							if(!ConvertState.BGM.playing()) ConvertState.BGM.play();
						}
					}
				} finally {
					instream.close();
				}
			}
			 Thread.sleep(2000); 
		}			
		} catch(InterruptedException | IOException e) {
			System.out.println("Error connecting to server");
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

	@Override
	public void run() {
		Start();
	}
}