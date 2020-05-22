package com.owen1212055.helpbot.components.reactions;

import com.owen1212055.helpbot.instance.BotInstance;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

import java.util.TimerTask;

public class ReactionWait extends TimerTask {
    private long user;
    private long message;
    private long channel;
    private ReactionResponder responder;

    public ReactionWait(long user, long channel, long message, ReactionResponder responder) {
        this.user = user;
        this.message = message;
        this.channel = channel;
        this.responder = responder;
    }

    @Override
    public void run() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Reaction request timed out...");
        try {
            Message message = BotInstance.getJda().getTextChannelById(this.channel).retrieveMessageById(this.message).complete();
            message.clearReactions().queue();
            message.editMessage(builder.build()).override(true).queue();
        } catch (ErrorResponseException suppressed) {
        }
    }

    public long getUser() {
        return user;
    }

    public long getChannel() {
        return channel;
    }

    public long getMessage() {
        return message;
    }

    public ReactionResponder getResponder() {
        return responder;
    }
}
