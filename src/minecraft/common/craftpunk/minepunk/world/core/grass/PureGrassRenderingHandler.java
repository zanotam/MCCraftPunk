package common.craftpunk.minepunk.world.core.grass;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PureGrassRenderingHandler implements ISimpleBlockRenderingHandler
{
	public int renderID;
	
	public PureGrassRenderingHandler(int id)
	{
		this.renderID = id;
	}

	/**
	 * basically stolen from renderBlockAsItem
	 */
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		float par3 = 0.5f;
		int par2 = metadata;
		
        Tessellator tessellator = Tessellator.instance;
        boolean flag = block.blockID == Block.grass.blockID;
        //flag = true; // because grass

        if (block == Block.dispenser || block == Block.dropper || block == Block.furnaceIdle)
        {
            par2 = 3;
        }

        int j;
        float f1;
        float f2;
        float f3;

        if (renderer.useInventoryTint)
        {
            j = block.getRenderColor(par2);

            if (flag)
            {
                j = 16777215;
            }

            f1 = (float)(j >> 16 & 255) / 255.0F;
            f2 = (float)(j >> 8 & 255) / 255.0F;
            f3 = (float)(j & 255) / 255.0F;
            GL11.glColor4f(f1 * par3, f2 * par3, f3 * par3, 1.0F);
        }

        j = block.getRenderType();
        renderer.setRenderBoundsFromBlock(block);
        int k;

		 block.setBlockBoundsForItemRender();
         renderer.setRenderBoundsFromBlock(block);
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
         tessellator.startDrawingQuads();
         tessellator.setNormal(0.0F, -1.0F, 0.0F);
         renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, par2));
         tessellator.draw();

         if (flag && renderer.useInventoryTint)
         {
             k = block.getRenderColor(par2);
             f2 = (float)(k >> 16 & 255) / 255.0F;
             f3 = (float)(k >> 8 & 255) / 255.0F;
             float f7 = (float)(k & 255) / 255.0F;
             GL11.glColor4f(f2 * par3, f3 * par3, f7 * par3, 1.0F);
         }

         tessellator.startDrawingQuads();
         tessellator.setNormal(0.0F, 1.0F, 0.0F);
         renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, par2));
         tessellator.draw();

         if (flag && renderer.useInventoryTint)
         {
             GL11.glColor4f(par3, par3, par3, 1.0F);
         }

         tessellator.startDrawingQuads();
         tessellator.setNormal(0.0F, 0.0F, -1.0F);
         renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, par2));
         tessellator.draw();
         tessellator.startDrawingQuads();
         tessellator.setNormal(0.0F, 0.0F, 1.0F);
         renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, par2));
         tessellator.draw();
         tessellator.startDrawingQuads();
         tessellator.setNormal(-1.0F, 0.0F, 0.0F);
         renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, par2));
         tessellator.draw();
         tessellator.startDrawingQuads();
         tessellator.setNormal(1.0F, 0.0F, 0.0F);
         renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, par2));
         tessellator.draw();
         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	/**
	 * basically a copy of renderStandardBlock
	 */
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
        int l = block.colorMultiplier(renderer.blockAccess, x, y, z); //slightly changed this line
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        //slightly changed the below line
        return Minecraft.isAmbientOcclusionEnabled() && Block.lightValue[block.blockID] == 0 ? (renderer.partialRenderBounds ? renderer.func_102027_b(block, x, y, z, f, f1, f2) : this.renderWithAmbientOcclusion(block, x, y, z, f, f1, f2, renderer)) : renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, f, f1, f2);
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderID;
	}

	/**
	 * basically a copy of renderStandardBlockWithAmbientOcclusion
	 * @param par1Block
	 * @param par2
	 * @param par3
	 * @param par4
	 * @param par5
	 * @param par6
	 * @param par7
	 * @param renderer
	 * @return
	 */
	public boolean renderWithAmbientOcclusion(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7, RenderBlocks renderer)
	{
			renderer.enableAO = true;
	        boolean flag = false;
	        float f3 = 0.0F;
	        float f4 = 0.0F;
	        float f5 = 0.0F;
	        float f6 = 0.0F;
	        boolean flag1 = true;
	        int l = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4);
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.setBrightness(983055);

	        if (renderer.hasOverrideBlockTexture())
	        {
	            flag1 = false;
	        }

	        boolean flag2;
	        boolean flag3;
	        boolean flag4;
	        boolean flag5;
	        float f7;
	        int i1;

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3 - 1, par4, 0))
	        {
	            if (renderer.renderMinY <= 0.0D)
	            {
	                --par3;
	            }

	            renderer.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3 - 1, par4)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3 - 1, par4)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 - 1, par4 + 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 - 1, par4 - 1)];

	            if (!flag4 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
	                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4 - 1);
	                renderer.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4 - 1);
	            }

	            if (!flag5 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
	                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4 + 1);
	                renderer.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4 + 1);
	            }

	            if (!flag4 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
	                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4 - 1);
	                renderer.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4 - 1);
	            }

	            if (!flag5 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
	                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4 + 1);
	                renderer.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4 + 1);
	            }

	            if (renderer.renderMinY <= 0.0D)
	            {
	                ++par3;
	            }

	            i1 = l;

	            if (renderer.renderMinY <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(par2, par3 - 1, par4))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4);
	            f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
	            f6 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
	            f5 = (f7 + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
	            f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, i1);
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, i1);

	            if (flag1)
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5 * 0.5F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6 * 0.5F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7 * 0.5F;
	            }
	            else
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
	            }

	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            renderer.renderFaceYNeg(par1Block, (double)par2, (double)par3, (double)par4, renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 0));
	            flag = true;
	        }

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3 + 1, par4, 1))
	        {
	            if (renderer.renderMaxY >= 1.0D)
	            {
	                ++par3;
	            }

	            renderer.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 + 1);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3 + 1, par4)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3 + 1, par4)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 + 1, par4 + 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 + 1, par4 - 1)];

	            if (!flag4 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
	                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4 - 1);
	                renderer.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4 - 1);
	            }

	            if (!flag4 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
	                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4 - 1);
	                renderer.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4 - 1);
	            }

	            if (!flag5 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
	                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4 + 1);
	                renderer.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4 + 1);
	            }

	            if (!flag5 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
	                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4 + 1);
	                renderer.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4 + 1);
	            }

	            if (renderer.renderMaxY >= 1.0D)
	            {
	                --par3;
	            }

	            i1 = l;

	            if (renderer.renderMaxY >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(par2, par3 + 1, par4))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4);
	            f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
	            f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
	            f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
	            f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
	            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5;
	            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6;
	            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7;
	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            renderer.renderFaceYPos(par1Block, (double)par2, (double)par3, (double)par4, renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 1));
	            flag = true;
	        }

	        Icon icon;

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3, par4 - 1, 2))
	        {
	            if (renderer.renderMinZ <= 0.0D)
	            {
	                --par4;
	            }

	            renderer.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoLightValueScratchYZNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoLightValueScratchYZPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4);
	            renderer.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoBrightnessYZNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoBrightnessYZPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4);
	            renderer.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3, par4 - 1)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3, par4 - 1)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 + 1, par4 - 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 - 1, par4 - 1)];

	            if (!flag2 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
	                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3 - 1, par4);
	                renderer.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3 - 1, par4);
	            }

	            if (!flag2 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
	                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3 + 1, par4);
	                renderer.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3 + 1, par4);
	            }

	            if (!flag3 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
	                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3 - 1, par4);
	                renderer.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3 - 1, par4);
	            }

	            if (!flag3 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
	                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3 + 1, par4);
	                renderer.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3 + 1, par4);
	            }

	            if (renderer.renderMinZ <= 0.0D)
	            {
	                ++par4;
	            }

	            i1 = l;

	            if (renderer.renderMinZ <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(par2, par3, par4 - 1))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 - 1);
	            f3 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
	            f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
	            f5 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
	            f6 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);

	            if (flag1)
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5 * 0.8F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6 * 0.8F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7 * 0.8F;
	            }
	            else
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
	            }

	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            icon = renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 2);
	            renderer.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, icon);

	            if (RenderBlocks.fancyGrass && icon.getIconName().equals("grass_side") && !renderer.hasOverrideBlockTexture())
	            {
	                renderer.colorRedTopLeft *= par5;
	                renderer.colorRedBottomLeft *= par5;
	                renderer.colorRedBottomRight *= par5;
	                renderer.colorRedTopRight *= par5;
	                renderer.colorGreenTopLeft *= par6;
	                renderer.colorGreenBottomLeft *= par6;
	                renderer.colorGreenBottomRight *= par6;
	                renderer.colorGreenTopRight *= par6;
	                renderer.colorBlueTopLeft *= par7;
	                renderer.colorBlueBottomLeft *= par7;
	                renderer.colorBlueBottomRight *= par7;
	                renderer.colorBlueTopRight *= par7;
	                renderer.renderFaceZNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
	            }

	            flag = true;
	        }

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2, par3, par4 + 1, 3))
	        {
	            if (renderer.renderMaxZ >= 1.0D)
	            {
	                ++par4;
	            }

	            renderer.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoLightValueScratchYZNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoLightValueScratchYZPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4);
	            renderer.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4);
	            renderer.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4);
	            renderer.aoBrightnessYZNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoBrightnessYZPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3, par4 + 1)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3, par4 + 1)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 + 1, par4 + 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2, par3 - 1, par4 + 1)];

	            if (!flag2 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
	                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3 - 1, par4);
	                renderer.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3 - 1, par4);
	            }

	            if (!flag2 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
	                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3 + 1, par4);
	                renderer.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3 + 1, par4);
	            }

	            if (!flag3 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
	                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3 - 1, par4);
	                renderer.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3 - 1, par4);
	            }

	            if (!flag3 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
	                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3 + 1, par4);
	                renderer.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3 + 1, par4);
	            }

	            if (renderer.renderMaxZ >= 1.0D)
	            {
	                --par4;
	            }

	            i1 = l;

	            if (renderer.renderMaxZ >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(par2, par3, par4 + 1))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 + 1);
	            f3 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
	            f6 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
	            f5 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
	            f4 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);

	            if (flag1)
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5 * 0.8F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6 * 0.8F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7 * 0.8F;
	            }
	            else
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
	            }

	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            icon = renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 3);
	            renderer.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 3));

	            if (RenderBlocks.fancyGrass && icon.getIconName().equals("grass_side") && !renderer.hasOverrideBlockTexture())
	            {
	                renderer.colorRedTopLeft *= par5;
	                renderer.colorRedBottomLeft *= par5;
	                renderer.colorRedBottomRight *= par5;
	                renderer.colorRedTopRight *= par5;
	                renderer.colorGreenTopLeft *= par6;
	                renderer.colorGreenBottomLeft *= par6;
	                renderer.colorGreenBottomRight *= par6;
	                renderer.colorGreenTopRight *= par6;
	                renderer.colorBlueTopLeft *= par7;
	                renderer.colorBlueBottomLeft *= par7;
	                renderer.colorBlueBottomRight *= par7;
	                renderer.colorBlueTopRight *= par7;
	                renderer.renderFaceZPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
	            }

	            flag = true;
	        }

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2 - 1, par3, par4, 4))
	        {
	            if (renderer.renderMinX <= 0.0D)
	            {
	                --par2;
	            }

	            renderer.aoLightValueScratchXYNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoLightValueScratchXZNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoLightValueScratchXZNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoLightValueScratchXYNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4);
	            renderer.aoBrightnessXYNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoBrightnessXZNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoBrightnessXZNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoBrightnessXYNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3 + 1, par4)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3 - 1, par4)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3, par4 - 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 - 1, par3, par4 + 1)];

	            if (!flag5 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
	                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4 - 1);
	                renderer.aoBrightnessXYZNNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4 - 1);
	            }

	            if (!flag4 && !flag2)
	            {
	                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
	                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4 + 1);
	                renderer.aoBrightnessXYZNNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4 + 1);
	            }

	            if (!flag5 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
	                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4 - 1);
	                renderer.aoBrightnessXYZNPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4 - 1);
	            }

	            if (!flag4 && !flag3)
	            {
	                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
	                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZNPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4 + 1);
	                renderer.aoBrightnessXYZNPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4 + 1);
	            }

	            if (renderer.renderMinX <= 0.0D)
	            {
	                ++par2;
	            }

	            i1 = l;

	            if (renderer.renderMinX <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(par2 - 1, par3, par4))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 - 1, par3, par4);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 - 1, par3, par4);
	            f6 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
	            f3 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
	            f4 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
	            f5 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);

	            if (flag1)
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5 * 0.6F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6 * 0.6F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7 * 0.6F;
	            }
	            else
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
	            }

	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            icon = renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 4);
	            renderer.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, icon);

	            if (RenderBlocks.fancyGrass && icon.getIconName().equals("grass_side") && !renderer.hasOverrideBlockTexture())
	            {
	                renderer.colorRedTopLeft *= par5;
	                renderer.colorRedBottomLeft *= par5;
	                renderer.colorRedBottomRight *= par5;
	                renderer.colorRedTopRight *= par5;
	                renderer.colorGreenTopLeft *= par6;
	                renderer.colorGreenBottomLeft *= par6;
	                renderer.colorGreenBottomRight *= par6;
	                renderer.colorGreenTopRight *= par6;
	                renderer.colorBlueTopLeft *= par7;
	                renderer.colorBlueBottomLeft *= par7;
	                renderer.colorBlueBottomRight *= par7;
	                renderer.colorBlueTopRight *= par7;
	                renderer.renderFaceXNeg(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
	            }

	            flag = true;
	        }

	        if (renderer.renderAllFaces || par1Block.shouldSideBeRendered(renderer.blockAccess, par2 + 1, par3, par4, 5))
	        {
	            if (renderer.renderMaxX >= 1.0D)
	            {
	                ++par2;
	            }

	            renderer.aoLightValueScratchXYPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoLightValueScratchXZPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoLightValueScratchXZPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoLightValueScratchXYPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4);
	            renderer.aoBrightnessXYPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4);
	            renderer.aoBrightnessXZPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 - 1);
	            renderer.aoBrightnessXZPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4 + 1);
	            renderer.aoBrightnessXYPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4);
	            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3 + 1, par4)];
	            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3 - 1, par4)];
	            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3, par4 + 1)];
	            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(par2 + 1, par3, par4 - 1)];

	            if (!flag2 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
	                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4 - 1);
	                renderer.aoBrightnessXYZPNN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4 - 1);
	            }

	            if (!flag2 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
	                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPNP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 - 1, par4 + 1);
	                renderer.aoBrightnessXYZPNP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 - 1, par4 + 1);
	            }

	            if (!flag3 && !flag4)
	            {
	                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
	                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPN = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4 - 1);
	                renderer.aoBrightnessXYZPPN = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4 - 1);
	            }

	            if (!flag3 && !flag5)
	            {
	                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
	                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
	            }
	            else
	            {
	                renderer.aoLightValueScratchXYZPPP = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2, par3 + 1, par4 + 1);
	                renderer.aoBrightnessXYZPPP = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3 + 1, par4 + 1);
	            }

	            if (renderer.renderMaxX >= 1.0D)
	            {
	                --par2;
	            }

	            i1 = l;

	            if (renderer.renderMaxX >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(par2 + 1, par3, par4))
	            {
	                i1 = par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2 + 1, par3, par4);
	            }

	            f7 = par1Block.getAmbientOcclusionLightValue(renderer.blockAccess, par2 + 1, par3, par4);
	            f3 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
	            f4 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
	            f5 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
	            f6 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
	            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
	            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
	            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
	            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);

	            if (flag1)
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = par5 * 0.6F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = par6 * 0.6F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = par7 * 0.6F;
	            }
	            else
	            {
	                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
	                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
	                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
	            }

	            renderer.colorRedTopLeft *= f3;
	            renderer.colorGreenTopLeft *= f3;
	            renderer.colorBlueTopLeft *= f3;
	            renderer.colorRedBottomLeft *= f4;
	            renderer.colorGreenBottomLeft *= f4;
	            renderer.colorBlueBottomLeft *= f4;
	            renderer.colorRedBottomRight *= f5;
	            renderer.colorGreenBottomRight *= f5;
	            renderer.colorBlueBottomRight *= f5;
	            renderer.colorRedTopRight *= f6;
	            renderer.colorGreenTopRight *= f6;
	            renderer.colorBlueTopRight *= f6;
	            icon = renderer.getBlockIcon(par1Block, renderer.blockAccess, par2, par3, par4, 5);
	            renderer.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, icon);

	            if (RenderBlocks.fancyGrass && icon.getIconName().equals("grass_side") && !renderer.hasOverrideBlockTexture())
	            {
	                renderer.colorRedTopLeft *= par5;
	                renderer.colorRedBottomLeft *= par5;
	                renderer.colorRedBottomRight *= par5;
	                renderer.colorRedTopRight *= par5;
	                renderer.colorGreenTopLeft *= par6;
	                renderer.colorGreenBottomLeft *= par6;
	                renderer.colorGreenBottomRight *= par6;
	                renderer.colorGreenTopRight *= par6;
	                renderer.colorBlueTopLeft *= par7;
	                renderer.colorBlueBottomLeft *= par7;
	                renderer.colorBlueBottomRight *= par7;
	                renderer.colorBlueTopRight *= par7;
	                renderer.renderFaceXPos(par1Block, (double)par2, (double)par3, (double)par4, BlockGrass.getIconSideOverlay());
	            }

	            flag = true;
	        }

	        renderer.enableAO = false;
	        return flag;
	    }
}
