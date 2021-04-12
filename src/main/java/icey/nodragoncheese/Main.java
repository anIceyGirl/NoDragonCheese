package icey.nodragoncheese;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.util.BedExplosionDamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "nodragoncheese";

    public Main() 
    {
    	MinecraftForge.EVENT_BUS.register(this);
    	IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    	
    	forgeEventBus.addListener(this::onEntityDamage);
    }
    
    private void onEntityDamage(final LivingHurtEvent event)
    {
    	if (isEnderDragon(event.getEntity()) && event.getSource() instanceof BedExplosionDamageSource)
    	{
    		event.setCanceled(true);
    	}
    }
    
    private boolean isEnderDragon(Entity entity)
    {
    	return (entity instanceof EnderDragonEntity || entity instanceof EnderDragonPartEntity);
    }
}
