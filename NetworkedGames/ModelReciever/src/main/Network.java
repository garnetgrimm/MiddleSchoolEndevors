package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

public class Network extends Thread {
	public String send_request(String request, String[] args) {
		String args_url = "";
		
		if(args.length > 0) args_url = "?";
		else args_url = "";
		
		for(int i = 0; i < args.length; i++) {
			args_url += args[i];
		}
		
		try {
		HttpClient httpclient = HttpClients.createDefault();
		//HttpPost httppost = new HttpPost("http://127.0.0.1:5000/" + request + args_url);
		HttpPost httppost = new HttpPost("http://127.0.0.1:5000/" + request);
		
		//just changed get to post, no testing so far 
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(int i = 0; i < args.length; i++) {
			String var  = args[i].split("=")[0];
			String value  = args[i].split("=")[1];
			params.add(new BasicNameValuePair(var, value));
		}
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String s = readStream(instream);
					if(request.equals("update") && args[0].equals("first_run=true")) {
						Init.connected = true;
						List<Arg> json_args = File_Handler.json_to_args(s);
						for(int i = 0; i < json_args.get(1).args.length; i++) {
							Init.newModel.addElement(json_args.get(1).args[i]);
						}
					} else {
						//System.out.println(s);
					}
					return(s);
				} finally {
					instream.close();
				}
			}
			 Thread.sleep(2000); 
		} catch(InterruptedException | IOException e) {
			return("Error connecting to server");
		}
		return "No response";
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
		while(true) {
			try {
				send_request("update", new String[] {"first_run=false"});
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}