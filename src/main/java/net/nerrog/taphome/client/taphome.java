package net.nerrog.taphome.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class taphome implements ClientModInitializer {

    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient(){
        //InitializeClient

        //Register Input Key Event
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.1taphome.home",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.1taphome.home"
        ));

        //KeyEvent
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()){
                Objects.requireNonNull(client.player).sendChatMessage("/home");
            }
        });
    }
}
