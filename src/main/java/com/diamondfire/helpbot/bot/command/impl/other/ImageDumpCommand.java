package com.diamondfire.helpbot.bot.command.impl.other;

import com.diamondfire.helpbot.bot.command.argument.ArgumentSet;
import com.diamondfire.helpbot.bot.command.help.*;
import com.diamondfire.helpbot.bot.command.impl.Command;
import com.diamondfire.helpbot.bot.command.permissions.Permission;
import com.diamondfire.helpbot.bot.command.reply.PresetBuilder;
import com.diamondfire.helpbot.bot.command.reply.feature.informative.*;
import com.diamondfire.helpbot.bot.events.CommandEvent;
import com.diamondfire.helpbot.sys.externalfile.ExternalFile;
import com.diamondfire.helpbot.util.FileUtil;

import java.io.*;

public class ImageDumpCommand extends Command {

    @Override
    public String getName() {
        return "imagedump";
    }

    @Override
    public HelpContext getHelpContext() {
        return new HelpContext()
                .description("Provides over a zip file containing all of the minecraft photos HelpBot uses. Each image is 300x300 and is rendered the exact same way it would in Minecraft.")
                .category(CommandCategory.OTHER);
    }

    @Override
    public ArgumentSet getArguments() {
        return new ArgumentSet();
    }

    @Override
    public Permission getPermission() {
        return Permission.USER;
    }

    @Override
    public void run(CommandEvent event) {
        File images = ExternalFile.IMAGES_DIR.getFile();
        PresetBuilder builder = new PresetBuilder();
        builder.withPreset(new InformativeReply(InformativeReplyType.INFO, "Please wait, the zip is being created."));
        event.replyA(builder).queue((msg) -> {
            try {
                File zip = FileUtil.zipFile(images.toPath(), "images.zip");
                event.getChannel().sendFile(zip).queue((fileMsg) -> {
                    msg.delete().queue();
                });
            } catch (IOException e) {
                PresetBuilder builderError = new PresetBuilder();
                builder.withPreset(new InformativeReply(InformativeReplyType.ERROR, "Failed to send zip file."));
                msg.editMessage(builderError.getEmbed().build()).queue();
            }
        });

    }

}

