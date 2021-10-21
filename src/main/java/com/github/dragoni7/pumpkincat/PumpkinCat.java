package com.github.dragoni7.pumpkincat;

import com.github.dragoni7.pumpkincat.client.ClientBusEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.dragoni7.pumpkincat.init.RegisterEntities;



@Mod(PumpkinCat.MODID)
public class PumpkinCat
{
	public static final String MODID = "pumpkincat";
    private static final Logger LOGGER = LogManager.getLogger();

    public PumpkinCat() {
    	
    	GeckoLib.initialize();

    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    	
        RegisterEntities.ENTITY_TYPES.register(modBus);
        MinecraftForge.EVENT_BUS.register(this);
        
        if (FMLEnvironment.dist == Dist.CLIENT) {
        	ClientBusEvents.subscribeClientEvents(modBus, forgeBus);
        }
    }
    
}