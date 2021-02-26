package technoturnovers.wolfbait;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class WolfArrowEntity extends ArrowEntity {
    public WolfArrowEntity(EntityType<? extends WolfArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public WolfArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public WolfArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Environment(EnvType.CLIENT)
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, WolfBaitClient.PacketID);
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);
        BlockPos TargetPos = target.getBlockPos().toImmutable();
        Predicate TargetNotSelf = x -> x != target;
        Box AggroRange = new Box(
                (double)TargetPos.getX() - 32, (double)TargetPos.getY() - 32, (double)TargetPos.getZ() - 32,
                (double)TargetPos.getX() + 32, (double)TargetPos.getY() + 32, (double)TargetPos.getZ() + 32
        );
        Predicate<?> TargetInRange = (Entity x) -> AggroRange.contains(x.getPos());
        List<WolfEntity> WolvesInRange = world.getEntitiesByType(EntityType.WOLF, AggroRange, TargetNotSelf.and(TargetInRange));
        for (WolfEntity doggo : WolvesInRange) {
            doggo.setAngryAt(target.getUuid());
            doggo.setAttacker(target);
            doggo.setTarget(target);
            doggo.chooseRandomAngerTime();
        }
    }

    protected ItemStack asItemStack() {
        return new ItemStack(WolfBait.WOLF_ARROW);
    }
}
