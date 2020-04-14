package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

public class Network implements Runnable {
	public static void Start() {
		
		boolean handshake = false;
		
		try {
		HttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost("http://localhost:5000/upload");
		//HttpGet httppost = new HttpGet("http://localhost:5000/upload?" + first_name_str + "&" + last_name_str + "&" + email_str + "&" + phone_num_str + "&" + obj_name);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("obj_name", Init.Attached_Files.get(0).name));
		params.add(new BasicNameValuePair("obj_code", Init.Attached_Files.get(0).encoding));
		params.add(new BasicNameValuePair("phone_num", Init.phone_num.getText()));
		params.add(new BasicNameValuePair("email", Init.email.getText()));
		params.add(new BasicNameValuePair("last_name", Init.last_name.getText()));
		params.add(new BasicNameValuePair("first_name", Init.first_name.getText()));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String s = readStream(instream);
					handshake = Boolean.parseBoolean(s);
				} finally {
					instream.close();
				}
			}
		} catch(IOException e) {
			System.out.println("Error connecting to server");
		}
		
		Object[] options = {"OK"};
		 
		 String MESSAGE = "";
		 String TITLE = "";
		 
		 if(handshake) { TITLE = "Success"; MESSAGE = "Object Sent! "; }
		 else { TITLE = "Failure"; MESSAGE = "Object did not send, check internet connection. "; }; //create popup
		 JOptionPane.showOptionDialog(
				  null,
                  MESSAGE , TITLE,
                  JOptionPane.PLAIN_MESSAGE,
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]
        );
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