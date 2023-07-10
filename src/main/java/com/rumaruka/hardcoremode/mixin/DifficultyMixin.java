package com.rumaruka.hardcoremode.mixin;

import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Difficulty.class)
public class DifficultyMixin {



    /**
     * @author Rumaruka
     * @reason by ID
     */
    @Overwrite
    public static Difficulty byId(int pId) {

        return Difficulty.HARD;
    }
    @Inject(method = "byId",cancellable = true,at=@At("HEAD"))
    private static void byId(int pId, CallbackInfoReturnable<Difficulty> cir) {
       cir.setReturnValue(Difficulty.HARD);
    }
}
