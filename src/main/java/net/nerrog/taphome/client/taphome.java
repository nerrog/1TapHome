package net.nerrog.taphome.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
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
                try {
                    if (client.player == null) return;
                    client.player.networkHandler.sendChatCommand("home");
                    Objects.requireNonNull(client.player).sendMessage(Text.of(I18n.translate("screen.1taphome.go_home")), true);
                } catch (Exception e) {
                    Objects.requireNonNull(client.player).sendMessage(Text.of("§c[1TapHome]error!\n" + e.getMessage()), false);
                }
            }
        });
    }
}
