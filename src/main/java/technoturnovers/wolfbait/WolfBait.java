package technoturnovers.wolfbait;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WolfBait implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "wolfbait";
    public static final String MOD_NAME = "WolfBait";

    public static final WolfArrow WOLF_ARROW = new WolfArrow(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final EntityType<WolfArrowEntity> WOLF_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WolfBait.MOD_ID, "wolf_arrow_entity"),
            FabricEntityTypeBuilder.<WolfArrowEntity>create(
                    SpawnGroup.MISC,
                    WolfArrowEntity::new
            ).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        Registry.register(Registry.ITEM, new Identifier(WolfBait.MOD_ID, "wolf_arrow"), WOLF_ARROW);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}