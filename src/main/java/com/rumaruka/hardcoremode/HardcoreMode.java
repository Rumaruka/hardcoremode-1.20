package com.rumaruka.hardcoremode;

import com.rumaruka.hardcoremode.init.HMSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.rumaruka.hardcoremode.HardcoreMode.MODID;

@Mod(MODID)
public class HardcoreMode {


    public static final String MODID = "hardcoremode";

    public HardcoreMode() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        HMSounds.REGISTER.register(eventBus);
    }


    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}
