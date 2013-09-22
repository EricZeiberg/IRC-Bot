package me.masterejay.bot;

import org.jibble.pircbot.*;

public class BotMain {

public static void main(String[] args) throws Exception {
        
        // Now start our bot up.
        Bot bot = new Bot();
        
        // Enable debugging output.
        bot.setVerbose(false);
        
        // Connect to the IRC server.
        bot.connect("irc.esper.net");

        // Join the #pircbot channel.
        bot.joinChannel("#overcastnetwork");
        
    }
}
