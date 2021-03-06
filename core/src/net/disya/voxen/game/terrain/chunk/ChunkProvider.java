package net.disya.voxen.game.terrain.chunk;

import net.disya.voxen.terrain.biome.Biome;
import net.disya.voxen.terrain.biome.IBiomeProvider;
import net.disya.voxen.terrain.block.IBlockProvider;
import net.disya.voxen.terrain.chunk.Chunk;
import net.disya.voxen.terrain.chunk.IChunkProvider;
import net.disya.voxen.utils.PositionUtils;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by nicklas on 5/8/14.
 */
public class ChunkProvider implements IChunkProvider {
    private static ArrayMap<Long, Chunk> chunks = new ArrayMap<Long, Chunk>();
    private final IBlockProvider blockProvider;
    private final IBiomeProvider biomeProvider;

    public ChunkProvider(IBlockProvider blockProvider, IBiomeProvider biomeProvider) {
        this.blockProvider = blockProvider;
        this.biomeProvider = biomeProvider;
    }

    @Override
    public Chunk getChunkAt(int x, int y, int z) {
        return null;
    }

    @Override
    public Chunk getChunkAt(long position) {
        return chunks.get(position);
    }

    @Override
    public ArrayMap.Values<Chunk> getAllChunks() {
        return chunks.values();
    }

    @Override
    public void createChunk(Vector3 worldPosition, int x, int z) {
        Biome biome = biomeProvider.getBiomeAt(x, z);
        //System.out.println("Got biome "+biome.getClass().getName());
        Chunk c = new LandscapeChunk(blockProvider, biome, worldPosition, x, z);
        chunks.put(PositionUtils.hashOfPosition(x, z), c);
    }
}
