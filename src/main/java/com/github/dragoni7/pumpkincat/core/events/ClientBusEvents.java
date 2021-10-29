package com.github.dragoni7.pumpkincat.core.events;

import com.github.dragoni7.pumpkincat.init.RegisterEntities;
import com.github.dragoni7.pumpkincat.client.entity.render.PumpkinCatRender;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;


public class ClientBusEvents {
	
		public static void subscribeClientEvents(IEventBus modBus, IEventBus forgeBus) {
			
			modBus.addListener(ClientBusEvents::registerEntityRenders);
			
		}

	
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RegisterEntities.PUMPKIN_CAT.get(), PumpkinCatRender::new);
	}
	
}
