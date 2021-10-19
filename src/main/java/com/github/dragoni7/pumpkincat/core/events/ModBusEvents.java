package com.github.dragoni7.pumpkincat.core.events;

import com.github.dragoni7.pumpkincat.PumpkinCat;
import com.github.dragoni7.pumpkincat.common.entities.PumpkinCatEntity;
import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PumpkinCat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
	@SubscribeEvent
	public static void Attributes(final EntityAttributeCreationEvent event) {
		event.put(RegisterEntities.PUMPKIN_CAT.get(), PumpkinCatEntity.customAttributes().build());
	}
	
	 @SubscribeEvent
	    public static void entityRegister(final RegistryEvent.Register<EntityType<?>> event) {
	    	
	    }
}
