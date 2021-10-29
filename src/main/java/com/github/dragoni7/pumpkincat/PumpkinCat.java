package com.github.dragoni7.pumpkincat;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;

import com.github.dragoni7.pumpkincat.core.events.ClientBusEvents;
import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import com.github.dragoni7.pumpkincat.util.PumpkinCatConfig;



@Mod(PumpkinCat.MODID)
public class PumpkinCat
{
	public static final String MODID = "pumpkincat";

    public PumpkinCat() {

    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    	
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PumpkinCatConfig.spec, "pumpkincat.toml");
        RegisterEntities.ENTITY_TYPES.register(modBus);
        forgeBus.register(this);
        GeckoLib.initialize();
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
        	ClientBusEvents.subscribeClientEvents(modBus, forgeBus);
        }
    }   
}