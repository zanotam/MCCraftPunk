package common.craftpunk.minepunk.machinery.weaponry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityElectrolaserBlast extends Entity
{
    public static double fpErrorLimit = Math.pow(10,-5);

    public int  length;
    
    public double radius;
    
    public double xOrigin;
    public double yOrigin;
    public double zOrigin;
    
    public double xFinal;
    public double yFinal;
    public double zfinal;
    
    public EntityElectrolaserBlast(World world, Entity firer, int maxDist)
    {
        this(world, firer, maxDist, 0.1);
    }
    
    public EntityElectrolaserBlast(World world, Entity firer, int maxDist, double width)
    {
        super(world);
        this.radius = width;
        this.xOrigin = firer.posX;
        this.yOrigin = firer.posY;
        this.zOrigin = firer.posZ;
        

        //the following code is based upon something I found online
        //yaw is the x-z plane angle
        //pitch is the y angle?
        double yaw = Math.toRadians(firer.rotationYaw+90);
        double pitch = Math.toRadians(-firer.rotationPitch);
        
        double xHat = Math.cos(yaw)*Math.cos(pitch);
        double yHat = Math.sin(pitch);
        double zHat = Math.sin(yaw)*Math.cos(pitch);
        
        
        int beamLength = 0;
        
        /*
         * The following loop and variables are used to trace the laser in the direction
         * of the beam checking for obstructions in case the beam must terminate early.
         */
        double xPosStart = firer.posX;
        double yPosStart = firer.posY;
        double zPosStart = firer.posZ;
   
        boolean hitBlock = false;

        while(beamLength < maxDist && !hitBlock)
        {
            double xDiff;
            double yDiff;
            double zDiff;
            
            int xNext;
            int yNext;
            int zNext;
            
            int xBlockCoordNext = nextBlockCoordAlongVec(xPosStart, xHat);
            int yBlockCoordNext = nextBlockCoordAlongVec(yPosStart, yHat);
            int zBlockCoordNext = nextBlockCoordAlongVec(zPosStart, zHat);
            
            //the amount of magnitude needed to reach the next block
            xDiff = Math.abs((xBlockCoordNext - xPosStart) / xHat);
            yDiff = Math.abs((yBlockCoordNext - yPosStart) / yHat);
            zDiff = Math.abs((zBlockCoordNext - zPosStart) / zHat);
            
            double beamLengthDiff = Math.min(xDiff, Math.min(yDiff, zDiff));
            double xPosEnd;
            double yPosEnd;
            double zPosEnd;
            
            if (beamLength + beamLengthDiff < maxDist)
            {
                beamLength += beamLengthDiff;
                xPosEnd = xPosStart + xHat*beamLengthDiff;
                yPosEnd = yPosStart + yHat*beamLengthDiff;
                zPosEnd = zPosStart + zHat*beamLengthDiff;
            }
            else if(beamLength + beamLengthDiff >= maxDist)
            {
                beamLength = maxDist;
                beamLengthDiff = maxDist - beamLength;
                
                xPosEnd = xOrigin + xHat*beamLength;
                yPosEnd = yOrigin + yHat*beamLength;
                zPosEnd = zOrigin + zHat*beamLength;
            }
            
            //calculations for the next block and potential intersection
            xPosEnd = xPosStart + xHat*beamLengthDiff;
            yPosEnd = yPosStart + yHat*beamLengthDiff;
            zPosEnd = zPosStart + zHat*beamLengthDiff;
            
            int xBlockCoordEnd = roundToBlockCoordAlongVec(xPosEnd, xHat);
            int yBlockCoordEnd = roundToBlockCoordAlongVec(yPosEnd, yHat);
            int zBlockCoordEnd = roundToBlockCoordAlongVec(zPosEnd, zHat);
            
            xPosEnd = Math.max(xPosEnd, xBlockCoordEnd);
            yPosEnd = Math.max(yPosEnd, yBlockCoordEnd);
            zPosEnd = Math.max(zPosEnd, zBlockCoordEnd);
            
            
            int blockID = world.getBlockId(xBlockCoordEnd, yBlockCoordEnd, zBlockCoordEnd);           
            Block block = Block.blocksList[blockID];
            AxisAlignedBB blockAABB = block.getCollisionBoundingBoxFromPool(world, xBlockCoordEnd, yBlockCoordEnd, zBlockCoordEnd);
            /*
            if(block.getLightOpacity(world, xBlockCoordEnd, yBlockCoordEnd, zBlockCoordEnd) > 1)
            {
                boolean hitX = (xPosStart < blockAABB.minX && xPosEnd > blockAABB.minX) || (xPosStart > blockAABB.minX && xPosStart < blockAABB.maxX) || (xPosEnd > blockAABB.minX && xPosEnd < blockAABB.maxX);
                boolean hitY = (yPosStart < blockAABB.minY && yPosEnd > blockAABB.minY) || (yPosStart > blockAABB.minY && yPosStart < blockAABB.maxY) || (yPosEnd > blockAABB.minY && yPosEnd < blockAABB.maxY);;
                boolean hitZ = (zPosStart < blockAABB.minZ && zPosEnd > blockAABB.minZ) || (zPosStart > blockAABB.minZ && zPosStart < blockAABB.maxZ) || (zPosEnd > blockAABB.minZ && zPosEnd < blockAABB.maxZ);;
                hitBlock = hitX && hitY && hitZ;
            }
            */
            AxisAlignedBB laserAABB = AxisAlignedBB.getAABBPool().getAABB(xOrigin, yOrigin, zOrigin, xPosEnd, yPosEnd, zPosEnd);
            hitBlock = laserAABB.intersectsWith(blockAABB);
        }
        

        
        
        int v = beamLength;
        
        //the vectors that 
        double x = beamLength*xHat;
        double y = beamLength*yHat;
        double z = beamLength*zHat;
        
        this.xFinal = x + xOrigin;
        this.yFinal = y + yOrigin;
        this.zfinal = z + zOrigin;
        
        //this.boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void entityInit()
    {
        //nothing here
    }
    

    
//    @Override
//    /**
//     * This method gets called when the entity kills another one.
//     */
//    public void onKillEntity(EntityLiving par1EntityLiving) 
//    {
//        // TODO Figure out if anything goes here.
//    }
    
    
    @Override
    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        //nothing here
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        //nothing here
    }
    
    
    public int roundBlockCoord(double xPos)
    {
        int xBlock;
        if ((xPos - Math.floor(xPos)) <= (1 - fpErrorLimit))
        {
            xBlock = (int) Math.round(Math.floor(xPos) + Math.copySign(xPos, 1.0f));
        }
        else if ((xPos - Math.floor(xPos)) <= fpErrorLimit)
        {
            xBlock = (int) Math.round(Math.floor(xPos) + Math.copySign(-xPos, 1.0f));
        }
        else
        {
            xBlock = (int) Math.round(Math.floor(xPos));
        }
        return xBlock;
    }
    
    public int roundToBlockCoordAlongVec(double xPos, double xHat)
    {
        int xBlock;
        //checks if xPos is within fpErrorLimit of the next block and if so, 
        if ((xPos - Math.floor(xPos)) <= (1 - fpErrorLimit)) 
        {
            xBlock = Math.round(((float)Math.floor(xPos) + Math.copySign((float) xHat, 1.0f)));
        }
        else
        {
            xBlock= Math.round((float) Math.floor(xPos));
        }
        return xBlock;
    }
    
    public int nextBlockCoordAlongVec(double xPos, double xHat)
    {
        int xBlock;
        if ((xPos - Math.floor(xPos)) <= (1 - fpErrorLimit))
        {
            xBlock = Math.round(((float)Math.floor(xPos) + 2*Math.copySign((float) xHat, 1.0f)));
        }
        else
        {
            xBlock= Math.round(((float)Math.floor(xPos) + Math.copySign((float) xHat, 1.0f)));
        }
        return xBlock;
        
    }

    
}
