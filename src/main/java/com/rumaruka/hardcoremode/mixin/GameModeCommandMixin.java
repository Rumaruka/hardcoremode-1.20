package com.rumaruka.hardcoremode.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.GameModeCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(GameModeCommand.class)
public class GameModeCommandMixin {
    private static void logGamemodeChange(CommandSourceStack pSource, ServerPlayer pPlayer, GameType pGameType) {
        Component component = Component.literal("Don`t try it!!!!");
        if (pSource.getEntity() == pPlayer) {
            pSource.sendSuccess(()->Component.translatable("", component), true);
        } else {
            if (pSource.getLevel().getGameRules().getBoolean(GameRules.RULE_SENDCOMMANDFEEDBACK)) {
                pPlayer.sendSystemMessage(Component.translatable("", component));
            }

            pSource.sendSuccess(()->Component.translatable("", pPlayer.getDisplayName(), component), true);
        }

    }

@Inject(method = "setMode", cancellable = true, at=@At("HEAD"))
    private static void setMode(CommandContext<CommandSourceStack> pSource, Collection<ServerPlayer> pPlayers, GameType pGameType, CallbackInfoReturnable<Integer> cir) {
        int i = 0;

        for(ServerPlayer serverplayer : pPlayers) {
            if (serverplayer.setGameMode(GameType.SURVIVAL)) {
                logGamemodeChange(pSource.getSource(), serverplayer, GameType.SURVIVAL);
              cir.setReturnValue(++i);
            }
        }

    cir.setReturnValue(i);
}
}
