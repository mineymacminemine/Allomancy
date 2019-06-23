package com.legobmw99.allomancy.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIronNugget extends ThrowableEntity {
	private boolean dropItem = true;
	
    public EntityIronNugget(World par1World)
    {
        super(par1World);
    }

    public EntityIronNugget(World par1World, LivingEntity par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
        if (par2EntityLivingBase instanceof PlayerEntity){
        	PlayerEntity ep = (PlayerEntity) par2EntityLivingBase;
        	this.shoot(ep, ep.rotationPitch, ep.rotationYawHead, 2.0F, 4.5F, 3.5F);
        	if (ep.capabilities.isCreativeMode){
        		this.dropItem = false;
        	}
        }
    }

    public EntityIronNugget(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    
	@Override
	protected void onImpact(RayTraceResult movingobjectposition) {
		if (movingobjectposition.entityHit != null && movingobjectposition.entityHit != this.getThrower())
        {

            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 5);
        }

        if (!this.world.isRemote)
        {
        	ItemStack goldAmmo = new ItemStack(Items.IRON_NUGGET, 1, 0);
        	if(this.world.getGameRules().getBoolean("doTileDrops") && movingobjectposition.entityHit == null && this.dropItem){
				this.world.spawnEntity(new ItemEntity(this.world, this.posX, this.posY, this.posZ, goldAmmo));
			}
            this.setDead();
        }
	}


    
 

}
