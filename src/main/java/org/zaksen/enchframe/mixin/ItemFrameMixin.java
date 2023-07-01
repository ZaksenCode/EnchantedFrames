package org.zaksen.enchframe.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameMixin
{
    @Shadow private boolean fixed;

    @Shadow protected abstract void dropHeldStack(@Nullable Entity entity, boolean alwaysDrop);

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V", shift = At.Shift.BEFORE), cancellable = true)
    private void onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        if (player.isSneaking())
        {
            ItemFrameEntity frame = ((ItemFrameEntity) (Object) this);
            frame.setInvisible(!frame.isInvisible());
            this.fixed = !this.fixed;

            info.setReturnValue(ActionResult.SUCCESS);
        }
    }

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        ItemFrameEntity frame = ((ItemFrameEntity) (Object) this);
        frame.setInvisible(false);
        this.fixed = false;
    }
}
