package com.rumaruka.hardcoremode.events;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WarNotificationEvent {
    static boolean isWar = true;
    @SubscribeEvent
    public static void onPlayerIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (isWar && !ModList.get().isLoaded("simplegrinder") && player.getName().equals("Diamkey")) {

            player.displayClientMessage(Component.translatable("================"), false);
            player.displayClientMessage(Component.translatable(ChatFormatting.BLUE + "MAKE LOVE!"), false);
            player.displayClientMessage(Component.translatable(ChatFormatting.YELLOW + "NOT WAR!"), false);
            player.displayClientMessage(Component.translatable("================"), false);
            player.displayClientMessage(Component.translatable("=IF YOU WANT HELP="), false);
            player.displayClientMessage(ForgeHooks.newChatWithLinks("https://savelife.in.ua/"), false);
            player.displayClientMessage(ForgeHooks.newChatWithLinks("https://prytulafoundation.org/"), false);
            player.displayClientMessage(Component.translatable("================"), false);
        }

    }
}
