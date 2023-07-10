package com.rumaruka.hardcoremode.mixin;

import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameType.class)
public class GameTypeMixin {



    /**
     * @author Rumaruka
     * @reason by ID
     */
    @Overwrite
    public static GameType byId(int pId) {

        return GameType.SURVIVAL;
    }

}
