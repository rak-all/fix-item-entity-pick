package io.github.rakall.fixItemEntityPick.mixin;

import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntity.class)
public interface GetPickupDelay {
    @Accessor("pickupDelay")
    int getPickupDelay();
}
