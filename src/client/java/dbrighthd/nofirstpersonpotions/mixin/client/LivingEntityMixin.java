package dbrighthd.nofirstpersonpotions.mixin.client;

import dbrighthd.nofirstpersonpotions.NoFirstPersonPotionsClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

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

		if ((NoFirstPersonPotionsClient.getConfig().modEnabled) && camera != null && (Object)this == camera) {
			if(Minecraft.getInstance().options.getCameraType().isFirstPerson() || !NoFirstPersonPotionsClient.getConfig().showParticesInThirdPerson)
			{
				return; // don't spawn self effect particles
			}
		}

		level.addParticle(options, x, y, z, xd, yd, zd);
	}
}