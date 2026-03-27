package dbrighthd.nofirstpersonpotions.mixin.client;

import dbrighthd.nofirstpersonpotions.NoFirstPersonPotionsClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static dbrighthd.nofirstpersonpotions.config.ConfigManager.getConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow
	public abstract @Nullable LivingEntity asLivingEntity();

	@SuppressWarnings("ConstantValue") // the compiler doesnt know that options can change w/ config
    @Redirect(
			method = "tickEffects",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
			)
	)
	private void nofirstpersonpotions$skipSelfEffectParticles(Level level, ParticleOptions options, double x, double y, double z, double xd, double yd, double zd)
	{
		Minecraft mc = Minecraft.getInstance();
		Entity camera = mc.getCameraEntity();

		if ((getConfig().modEnabled) && camera != null && this.asLivingEntity() == camera) {
			if(Minecraft.getInstance().options.getCameraType().isFirstPerson() || !getConfig().showParticesInThirdPerson)
			{
				return; // don't spawn self effect particles
			}
		}

		level.addParticle(options, x, y, z, xd, yd, zd);
	}
}