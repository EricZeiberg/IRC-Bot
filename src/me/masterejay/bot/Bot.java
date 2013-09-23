package me.masterejay.bot;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import org.jibble.pircbot.PircBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Bot extends PircBot{
	Document doc = null;
	public Bot() {
		this.setName("HAL9000");
	}

	public void onMessage(String channel, String sender,
			String login, String hostname, String message) {
		if (message.equalsIgnoreCase("Hal: Open the pod bay doors")){
			this.sendMessage(channel, "Sorry " + sender + ", I'm afraid I can't do that...");
			this.sendAction(channel, "shuts down...");
		}
		
		if (message.contains("https://github.com/OvercastNetwork/Issues/")){
			try {
				Document urlDoc = doc = Jsoup.connect(message).ignoreContentType(true).userAgent("Mozilla").get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.sendMessage(channel, "Something went wrong! Did you type the correct link?");
			}
			Elements a = doc.getElementsByClass("discussion-topic-title");
			String b = a.text();
			this.sendMessage(channel, b);
		}
		if (message.equalsIgnoreCase("Bot: Leave")) {
			leave(sender, channel);
			
		}
		
	}
	
	public void leave(String sender, String channel){
		if (!(sender.equalsIgnoreCase("MasterEjay"))){
			this.sendMessage(channel, "You have no perms!");
			return;
		}
		disconnect();
		System.exit(1);
	}
	
	
}
