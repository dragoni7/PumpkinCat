package com.github.dragoni7.pumpkincat.client;

import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import com.github.dragoni7.pumpkincat.client.entity.render.PumpkinCatRender;
import com.github.dragoni7.pumpkincat.client.model.ModelPumpkinCat;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;


public class ClientBusEvents {
	
		public static void subscribeClientEvents(IEventBus modBus, IEventBus forgeBus) {
			
			modBus.addListener(ClientBusEvents::registerEntityRenders);
			modBus.addListener(ClientBusEvents::registerLayerDefinitions);
		}

	
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RegisterEntities.PUMPKIN_CAT.get(), PumpkinCatRender::new);
	}
	
	
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelPumpkinCat.LAYER_LOCATION, ModelPumpkinCat::createBodyLayer);
	}
}
