package me.masterejay.bot;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibble.pircbot.PircBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Bot extends PircBot{
	Document doc = null;
	public Bot() {
		this.setName("McStatusBot");
	}

	public void onMessage(String channel, String sender,
			String login, String hostname, String message) {
		if (message.equalsIgnoreCase("Bot: Mcstatus")){
					this.sendMessage(channel, "Poking jeb...");
					if (getMC() == 7){
						this.sendMessage(channel, "Jeb reports that all is well and all servers are up!");
					}
					else {
						this.sendMessage(channel, "Jeb reports that something is wrong and one or more of our servers is down.");
					}
		}
		if (message.equalsIgnoreCase("Bot: Leave")){
			//this.sendMessage(channel, sender);
				disconnect();
				System.exit(1);
		}
	}
	
	public int getMC(){
		 try {
				doc = Jsoup.connect("http://status.mojang.com/check").ignoreContentType(true).userAgent("Mozilla").get();
			} catch (IOException e) {

				//e.printStackTrace();
				this.sendMessage("#overcastnetwork", "Seems like something went wrong! :(");
			}
		 Element a = doc.body();
			String as = a.toString();
			String ab = as.replace("<body>", " ");
			String ab1 = ab.replace("</body>", " ");
			int i = getWordCount("green", ab1);
			return i;
	}
	
	private static int getWordCount(String word,String source){
        int count = 0;
        {
            Pattern p = Pattern.compile(word);
            Matcher m = p.matcher(source);
            while(m.find()) count++;
        }
        return count;
    }

	
	
}
