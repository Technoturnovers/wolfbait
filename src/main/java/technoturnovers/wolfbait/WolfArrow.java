package technoturnovers.wolfbait;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WolfArrow extends ArrowItem {
    public WolfArrow(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        WolfArrowEntity WolfArrowEntity = new WolfArrowEntity(world, shooter);
        WolfArrowEntity.initFromStack(stack);
        return WolfArrowEntity;
    }
}
