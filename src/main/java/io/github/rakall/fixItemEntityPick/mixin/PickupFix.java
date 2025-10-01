package io.github.rakall.fixItemEntityPick.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.github.rakall.fixItemEntityPick.util.iPickupFix;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HopperBlockEntity.class)
public class PickupFix implements iPickupFix {
    @WrapMethod(method = "extract(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/ItemEntity;)Z")
        private static boolean wrapExtract(Inventory inventory, ItemEntity itemEntity, Operation<Boolean> original) {
        if (((GetPickupDelay) itemEntity).getPickupDelay() < 0) {
            return false;
        }
        return original.call(inventory, itemEntity);
    }
}
