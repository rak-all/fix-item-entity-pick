package io.github.rakall.fixItemEntityPick.mixin;

import io.github.rakall.fixItemEntityPick.util.iEntityDiscardMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityDiscardMixin implements iEntityDiscardMixin {

    @Inject(method = "discard", at = @At("HEAD"), cancellable = true)
    private void onDiscard(CallbackInfo ci) {
        if ((Object)this instanceof ItemEntity itemEntity) {
            int pickupDelay = ((iItemEntityStackMixin)itemEntity).getPickupDelay();
            if (pickupDelay < 0) {
                ci.cancel();
                return;
            }
        }
    }
}
