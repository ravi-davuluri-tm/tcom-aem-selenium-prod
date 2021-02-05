package com.toy.gmail;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.toy.selenium.core.Configuration;

public class GmailAPI {

	private static String host = "pop.gmail.com";
	private static String port = "995";
	private static String AllowExistingMail = "off";

	/**
	 * Create connection to connect with email
	 * @param user        : gmail email id
	 * @param password    : gmail emaid password
	 * @return
	 * @throws Exception 
	 */
	public static Store getConnection(String userName, String password) throws Exception{
		AllowExistingMail = Configuration.readApplicationFile("AllowExistingMail");
				
		Properties properties = new Properties();		
		properties.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(properties, null);
		//Session emailSession = Session.getDefaultInstance(properties);		

		// create the POP3 store object and connect with the pop server
		Store store = session.getStore("imaps");
		store.connect(host, userName, password);
		return store;
	}
	
	/**
	 * Reading email of of gmail user
	 * 
	 * @param user        : gmail email id
	 * @param password    : gmail email password
	 * @param from        : sender email id
	 * @param messageText : this contains peace of message body text
	 * @return : it will return mail content
	 */
	public static String check(String userName, String password, String from, String messageText) {
		try {
			
			boolean status = true;
			Folder emailFolder;
			int count = 0;
			do {
				Store store = getConnection(userName, password);
				// create the folder object and open it
				if(AllowExistingMail.equalsIgnoreCase("on"))
					emailFolder = store.getFolder("QA");
				else
					emailFolder = store.getFolder("inbox");
				emailFolder.open(Folder.READ_WRITE);
				Message[] messages = emailFolder.getMessages();			
				for (int i = 0, n = messages.length; i < n; i++) {
					Message message = messages[i];
					if (message.getFrom()[0].toString().contains(from)
							&& message.getContent().toString().toLowerCase().contains(messageText.toLowerCase())) {
						System.out.println("Mail received");
						return message.getContent().toString().toLowerCase();
					}
				}
				Thread.sleep(5000);
				if (count > 0)
					status = false;
				count++;
				// close the store and folder objects
				emailFolder.close(true);
				store.close();
				System.out.println("waiting for mail to receive " + 5000 * count + " milliseconds");
			} while (status);

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Not found";

	}

	/**
	 * change flag to delete of all received emails for gmail's user
	 * 
	 * @param user     : gamail user id
	 * @param password : gmail user password
	 * @throws Exception 
	 */
	public static void delete(String userName, String password) throws Exception {
		try {
			Store store = getConnection(userName, password);
			Folder emailFolder = store.getFolder("inbox");
			emailFolder.open(Folder.READ_WRITE);

			//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// retrieve the messages from the folder in an array and print it\
			Message[] messages = emailFolder.getMessages();

			System.out.println("messages.length---" + messages.length);
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				String subject = message.getSubject();				
				// set the DELETE flag to true\
				message.setFlag(Flags.Flag.DELETED, true);
				System.out.println("Marked DELETE for message: " + subject);
				
			}
			// expunges the folder to remove messages which are marked deleted\
			emailFolder.close(true);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		//String username = "icxtoyota@gmail.com"; // change accordingly
		// change accordingly
		String username = "icx.dallas.testing@gmail.com"; // change accordingly
		String password = "Testing123!"; // change accordingly

		String from = "tmsusaincsaleslead@tmsusaconnect.com";

		String mailContent = check(username, password, from, "TestUS desktop RAQ Local Specials");
		System.out.println(mailContent);
		//delete(username, password);
	}

}
