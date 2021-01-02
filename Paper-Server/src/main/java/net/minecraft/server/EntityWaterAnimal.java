package net.minecraft.server;

public abstract class EntityWaterAnimal extends EntityInsentient implements IAnimal {

    public EntityWaterAnimal(World world) {
        super(world);
    }

    public boolean bN() {
        return true;
    }

    public boolean P() {
        // Paper start - Don't let water mobs spawn in non-water blocks
        // Based around EntityAnimal's implementation
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.getBoundingBox().b); // minY of bounding box
        int k = MathHelper.floor(this.locZ);
        Block block = this.world.getType(new BlockPosition(i, j, k)).getBlock();

        return block == Blocks.WATER || block == Blocks.FLOWING_WATER;
        // Paper end
    }

    public boolean canSpawn() {
        return this.world.a(this.getBoundingBox(), (Entity) this);
    }

    public int C() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return true;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }

    public void Y() {
        int i = this.getAirTicks();

        super.Y();
        if (this.isAlive() && !this.isInWater()) {
            --i;
            this.setAirTicks(i);
            if (this.getAirTicks() == -20) {
                this.setAirTicks(0);
                this.damageEntity(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAirTicks(300);
        }

    }

    public boolean bo() {
        return false;
    }
}
