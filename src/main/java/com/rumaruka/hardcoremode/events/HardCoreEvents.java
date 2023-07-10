package com.rumaruka.hardcoremode.events;

import com.rumaruka.hardcoremode.init.HMSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber
public class HardCoreEvents {
    private static boolean isFullHP;

    @SubscribeEvent
    public static void onServerLevelTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            event.getServer().getAllLevels().forEach(serverLevel -> serverLevel.getServer().setDifficulty(Difficulty.HARD, true));
            event.getServer().getAllLevels().forEach(serverLevel -> serverLevel.getServer().setDifficultyLocked(true));
            event.getServer().getAllLevels().forEach(serverLevel -> serverLevel.getServer().setDefaultGameType(GameType.SURVIVAL));
        }
    }


    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity entity = event.getSource().getEntity();

        if (living instanceof Zombie || living instanceof AbstractSkeleton) {

            if (entity instanceof Player player) {

                ItemStack mainHandItem = player.getMainHandItem();
                if (!mainHandItem.isEmpty() && mainHandItem.getItem() instanceof SwordItem) {
                    Map<Enchantment, Integer> enchantment = EnchantmentHelper.getEnchantments(mainHandItem);
                    if (!(enchantment.containsKey(Enchantments.SMITE))) {

                        event.setCanceled(true);
                    }

                }
            } else {
                event.setCanceled(true);
            }

        }
        if (living instanceof Silverfish || living instanceof Spider || living instanceof Endermite) {

            if (entity instanceof Player player) {

                ItemStack mainHandItem = player.getMainHandItem();
                if (!mainHandItem.isEmpty() && mainHandItem.getItem() instanceof SwordItem) {
                    Map<Enchantment, Integer> enchantment = EnchantmentHelper.getEnchantments(mainHandItem);
                    if (!(enchantment.containsKey(Enchantments.BANE_OF_ARTHROPODS))) {

                        event.setCanceled(true);
                    }

                }
            } else {
                event.setCanceled(true);
            }

        }
        if (living instanceof Blaze){
            if (!(event.getSource().getDirectEntity() instanceof Snowball)){
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {


        if (event.player.getHealth() > 4f) {
            isFullHP = true;
        }
        if (event.phase == TickEvent.Phase.END && event.player.level().getGameTime() % 100 == 0 && isFullHP) {
            if (!event.player.getAbilities().instabuild) {
                event.player.hurt(event.player.level().damageSources().generic(), 1f);
                event.player.level().playSound(null, event.player.getX(), event.player.getY(), event.player.getZ(), HMSounds.TAKE_HEALTH.get(), SoundSource.MASTER, 2.0F, 1.0F);

            }

            if (event.player.getHealth() <= 4f) {
                isFullHP = false;
            }

        }


    }


}
