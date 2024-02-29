package com.maharjan.amit.chat.command;

import java.util.HashMap;

public class ChatCommandFactory {
    private static HashMap<String, ChatCommand> commands = init();

    private static HashMap<String, ChatCommand> init() {
        HashMap<String, ChatCommand> cmds = new HashMap<>();
        cmds.put("exit", new ExitCommand());
        cmds.put("pm", new PMCommand());
        cmds.put("list", new ListCommand());
        cmds.put("block", new BlockCommand());
        return cmds;
    }

    public static ChatCommand get(String param) {
        return commands.containsKey(param) ? commands.get(param) : null;
    }
}
