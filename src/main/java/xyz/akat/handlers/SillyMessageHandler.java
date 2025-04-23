package xyz.akat.handlers;

import discord4j.core.object.entity.Message;

public class SillyMessageHandler extends AbstractMessageHandler {

    public SillyMessageHandler() {
        super("am i a silly boi");
    }

    @Override
    void onMessage(Message message) {

        super.replyToMessageInChannel("Yes. <_<");
    }
}
