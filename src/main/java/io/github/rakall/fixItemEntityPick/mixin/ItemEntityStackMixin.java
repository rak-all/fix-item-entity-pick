package io.github.rakall.fixItemEntityPick.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemEntity.class)
public abstract class ItemEntityStackMixin implements iItemEntityStackMixin {

    @Shadow
    private int pickupDelay;

    @Inject(method = "getStack", at = @At("HEAD"), cancellable = true)
    private void GetStack(CallbackInfoReturnable<ItemStack> cir) {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            String className = element.getClassName();

            if (className.contains("HopperBlockEntity")) {
                if (this.pickupDelay < 0) {
                    cir.setReturnValue(ItemStack.EMPTY);
                    return;
                }
            }
        }
    }

    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void onSetStack(ItemStack stack, CallbackInfo ci) {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            String className = element.getClassName();

            if (className.contains("HopperBlockEntity")) {
                if (this.pickupDelay < 0) {
                    ci.cancel();
                    return;
                }
            }
        }
    }

}
