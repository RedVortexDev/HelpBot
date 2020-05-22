package com.owen1212055.helpbot.command.commands.filespitter;

import com.owen1212055.helpbot.command.arguments.Argument;
import com.owen1212055.helpbot.command.arguments.NoArg;
import com.owen1212055.helpbot.command.permissions.Permission;
import com.owen1212055.helpbot.components.codedatabase.db.CodeDatabase;
import com.owen1212055.helpbot.events.CommandEvent;

public class PotionListCommand extends AbstractFileListCommand{
    @Override
    public String getName() {
        return "potions";
    }

    @Override
    public String getDescription() {
        return "Generates a file that contains all current potions.";
    }

    @Override
    public Argument getArgument() {
        return new NoArg();
    }

    @Override
    public Permission getPermission() {
        return Permission.USER;
    }

    @Override
    public void run(CommandEvent event) {

        super.generate(event, CodeDatabase.getPotions());
    }
}
