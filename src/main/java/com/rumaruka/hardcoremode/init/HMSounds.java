package com.rumaruka.hardcoremode.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rumaruka.hardcoremode.HardcoreMode.MODID;
import static com.rumaruka.hardcoremode.HardcoreMode.rl;

public class HMSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);


    public static final RegistryObject<SoundEvent> TAKE_HEALTH = REGISTER.register("hardcoremode.take_health",
            () ->  SoundEvent.createVariableRangeEvent(rl("hardcoremode.take_health")));
}
