package com.stormister.rediscovered;

import java.util.Random;
import java.io.PrintStream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
 
public class WorldGenSkySpawn extends WorldGenerator
{
   public WorldGenSkySpawn()
   {
     
   }
   
   public boolean generate(World world, Random rand, BlockPos pos)
	  {
	   int x = pos.getX();
	   int y = pos.getY();
	   int z = pos.getZ();
		   if(world.getBlockState(new BlockPos(x, y, z))== Blocks.planks.getDefaultState() || world.getBlockState(new BlockPos(x, y, z))== Blocks.log || world.getBlockState(new BlockPos(x, y, z))== Blocks.wool.getDefaultState() || world.getBlockState(new BlockPos(x, y, z))== Blocks.cobblestone.getDefaultState())
		   {
		      return false;
		   }
	   
		   	Block planks = Blocks.planks;
		   	Block bed = Blocks.bed;
		   	Block chair = mod_Rediscovered.Chair;
		   	Block table = mod_Rediscovered.Table;
		   	Block crafting = Blocks.crafting_table;
		   	Block furnace = Blocks.furnace;
		   	Block torch = Blocks.torch;
		   	Block glass = Blocks.glass;
		   	Block stone = Blocks.cobblestone;
		   	Block door = Blocks.oak_door;
		   	Block wool = Blocks.wool;
		   	Block wood = Blocks.log;
		   	Block blue = Blocks.lapis_block;
		   	Block grass = Blocks.grass;
		   	Block dirt = Blocks.dirt;
		   	Block brick = Blocks.stonebrick;
		   	Block fence = Blocks.iron_bars;
		   	Block netherrack = Blocks.netherrack;
		   	Block fire = Blocks.fire;
           
            
            //Air
            for(int a = 1; a <= 5; a++)
            	for(int b = 0; b <= 4; b++)
            		for(int c = -1; c <= 3; c++)
            			world.setBlockState(new BlockPos(x + a, y + b, z + c), Blocks.air.getDefaultState());
            
           
            //Grass
            //Front
            world.setBlockState(new BlockPos(x+7, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z-2), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z-1), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+1), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+2), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+5), grass.getDefaultState());
            
            //Right Side
            world.setBlockState(new BlockPos(x-1, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+1, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+2, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+3, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+4, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+5, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+6, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z-4), grass.getDefaultState());

            world.setBlockState(new BlockPos(x-1, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+1, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+2, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+3, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+4, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+5, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+6, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z-3), grass.getDefaultState());
            
            //Left Side
            world.setBlockState(new BlockPos(x-1, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+1, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+2, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+3, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+4, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+5, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+6, y - 1 , z+5), grass.getDefaultState());
            world.setBlockState(new BlockPos(x+7, y - 1 , z+5), grass.getDefaultState());
            
            //Back
            world.setBlockState(new BlockPos(x - 1, y - 1 , z-4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z-3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z-2), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z-1), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z+1), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z+2), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z+3), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z+4), grass.getDefaultState());
            world.setBlockState(new BlockPos(x - 1, y - 1 , z+5), grass.getDefaultState());
            
            
            
            //Ground
            world.setBlockState(new BlockPos(x + 6, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z-2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z-1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z+1), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z+2), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z+3), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -2, z+4), dirt.getDefaultState());
            world.setBlockState(new BlockPos(x, y -2, z+4), dirt.getDefaultState());
           
            
            
            // Bottom Ring
           
            world.setBlockState(new BlockPos(x, y , z), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y , z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y , z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y, z + 4), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y , z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y , z + 4), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y, z - 2), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x , y, z - 2), wood.getDefaultState());
            world.setBlockState(new BlockPos(x , y, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x , y, z ), planks.getDefaultState());
           
            // Floor
           
            world.setBlockState(new BlockPos(x, y -1 , z), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y -1 , z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y - 1, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y -1 , z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x, y - 1, z + 4), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y - 1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y - 1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -1 , z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y - 1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1 , z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y - 1, z + 4), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z - 2), wood.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y -1, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x , y -1, z - 2), wood.getDefaultState());
            world.setBlockState(new BlockPos(x , y -1, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x , y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 1 , y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 1 , y -1, z + 1 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z + 1 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z + 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 1), planks.getDefaultState());
           
           
            world.setBlockState(new BlockPos(x + 1 , y -1, z + 2 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z + 2 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z + 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 2), planks.getDefaultState());
           
           
            world.setBlockState(new BlockPos(x + 1 , y -1, z + 3 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z + 3 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z + 3), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 3), planks.getDefaultState());

            world.setBlockState(new BlockPos(x + 1 , y -1, z + 4 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z + 4 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z + 4), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z + 4), planks.getDefaultState());

            world.setBlockState(new BlockPos(x + 1 , y -1, z - 1 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z - 1 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z -1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z - 1), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z - 1), planks.getDefaultState());
           
            world.setBlockState(new BlockPos(x + 1 , y -1, z - 2 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 3, y -1, z - 2 ), planks.getDefaultState());
            world.setBlockState(new BlockPos(x  + 4, y -1, z -2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 5, y -1, z - 2), planks.getDefaultState());
            world.setBlockState(new BlockPos(x + 6, y -1, z - 2), planks.getDefaultState());
            
            
            //Carpet
            world.setBlockState(new BlockPos(x + 4, y -1, z), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -1, z), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -1, z+1), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -1, z+1), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z+1), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 4, y -1, z+2), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 3, y -1, z+2), wool.getDefaultState());
            world.setBlockState(new BlockPos(x + 2, y -1, z+2), wool.getDefaultState());

           
            //Contents of house
            world.setBlockState(new BlockPos(x + 2, y, z + 3), bed.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT).withProperty(BlockBed.FACING, EnumFacing.WEST), 3);
            world.setBlockState(new BlockPos(x + 1, y, z + 3), bed.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD).withProperty(BlockBed.FACING, EnumFacing.WEST), 1 + 3);
            world.setBlockState(new BlockPos(x + 1, y, z + 2), table.getDefaultState());
            world.setBlockState(new BlockPos(x + 1, y, z - 1), chair.getDefaultState().withProperty(BlockChair.FACING, EnumFacing.SOUTH), 3);


            //Second Ring

                    world.setBlockState(new BlockPos(x, y + 1 , z), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 1, z + 1), glass.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 1, z + 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 1, z + 3), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 1, z + 4), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 1, y + 1, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 1, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 3, y + 1, z + 4), glass.getDefaultState());
                    world.setBlockState(new BlockPos(x + 4, y + 1, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 1, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 1, z + 4), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 1, z + 3), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 1, z + 2), planks.getDefaultState());
                    
                    
                    
                    
                    //door
                    world.setBlockState(new BlockPos(x + 6, y, z + 1), door.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.WEST), 3);
                    world.setBlockState(new BlockPos(x + 6, y+1, z + 1), door.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.WEST), 3);
                    
                    
                    
                    
                    world.setBlockState(new BlockPos(x + 6, y + 1, z ), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 1, z - 1), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 1, z - 2), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 1, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 4, y + 1, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 1, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 1, y + 1, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 1, z - 2), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 1, z - 1), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 1, z ), planks.getDefaultState());

                    //Third Ring
                   
                    world.setBlockState(new BlockPos(x, y + 2 , z), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 2, z + 1), glass.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 2, z + 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 2, z + 3), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 2, z + 4), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 1, y + 2, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 2, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 3, y + 2, z + 4), glass.getDefaultState());
                    world.setBlockState(new BlockPos(x + 4, y + 2, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 2, z + 4), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z + 4), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z + 3), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z + 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z + 1), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z ), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z - 1), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y + 2, z - 2), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 2, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 4, y + 2, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 3, y + 2, z - 2), glass.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 2, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x + 1, y + 2, z - 2), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 2, z - 2), wood.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 2, z - 1), planks.getDefaultState());
                    world.setBlockState(new BlockPos(x , y + 2, z ), planks.getDefaultState());
           
                    //Roof
                   
                    world.setBlockState(new BlockPos(x + 1 , y +3, z + 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z + 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z + 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z + 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z + 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y  + 4, z + 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z + 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y -1, z + 2), planks.getDefaultState());
                   
                   
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z + 3 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z + 3), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z + 3 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z + 3 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z + 3 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z + 3), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z + 3), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6, y -1, z + 3), planks.getDefaultState());
       
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z + 4 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z + 4), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 3, z + 4), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z + 4 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 4, z + 4 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 3, z + 4 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z + 4), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 3, z + 4), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z + 4), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z + 4 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z + 4 ), stone.getDefaultState());
       
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z - 1 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z - 1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z - 1 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z -1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z - 1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z - 1 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z -1 ), stone.getDefaultState());
                   
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z - 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z - 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 3, z - 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z - 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 4, z - 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 3, z - 2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z -2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 3, z -2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z - 2), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z -2 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z - 2 ), stone.getDefaultState());
                   
                   
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z  ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z  ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 6 , y +3, z  ), stone.getDefaultState());
                   
                    world.setBlockState(new BlockPos(x + 1 , y + 3, z + 1 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 2, y + 4, z + 1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 3, y + 5, z + 1 ), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x  + 4, y + 4, z + 1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x + 5, y + 3, z + 1), stone.getDefaultState());
                    world.setBlockState(new BlockPos(x, y + 3, z + 1 ), stone.getDefaultState());
                     world.setBlockState(new BlockPos(x + 6 , y +3, z + 1 ), stone.getDefaultState());
                     
                     
                     
                     //Fireplace
                     //Fence
                     world.setBlockState(new BlockPos(x+2, y, z-1), fence.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y, z-1), fence.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y, z-1), fence.getDefaultState());
                     //Front
                     world.setBlockState(new BlockPos(x+3, y-1, z-2), netherrack.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y, z-2), fire.getDefaultState());
                     world.setBlockState(new BlockPos(x+2, y, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+2, y+1, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y+1, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+2, y+2, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+2, z-2), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y+2, z-2), brick.getDefaultState());
                     //back
                     world.setBlockState(new BlockPos(x+2, y, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+2, y+1, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+1, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y+1, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+2, y+2, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+2, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+4, y+2, z-3), brick.getDefaultState());
                     
                     world.setBlockState(new BlockPos(x+3, y+3, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+4, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+5, z-3), brick.getDefaultState());
                     world.setBlockState(new BlockPos(x+3, y+6, z-3), brick.getDefaultState());
                     

	return true;
	
	}
}