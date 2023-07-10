package com.rumaruka.hardcoremode.mixin;


import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.DifficultyCommand;
import net.minecraft.world.Difficulty;
import org.jline.reader.SyntaxError;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DifficultyCommand.class)
public class CommandDifficultyMixin {
    private static final DynamicCommandExceptionType ERROR_ALREADY_DIFFICULT = new DynamicCommandExceptionType((p_136948_) -> {
        return Component.translatable("dont.use.command", new Object[]{p_136948_});
    });
    @Inject(method = "setDifficulty", at = @At("HEAD"), cancellable = true)
    private static void setDifficulty(CommandSourceStack pSource, Difficulty pDifficulty, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        MinecraftServer minecraftserver = pSource.getServer();
        int id = pDifficulty.getId();
        if (!( pDifficulty==Difficulty.HARD||id==3)) {
            throw ERROR_ALREADY_DIFFICULT.create(pDifficulty.getKey());
            
        } else {
            minecraftserver.setDifficulty(Difficulty.HARD, true);
            cir.setReturnValue(0);
        }
    }
}
