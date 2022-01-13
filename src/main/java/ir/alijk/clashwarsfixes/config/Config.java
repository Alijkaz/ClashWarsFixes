package ir.alijk.clashwarsfixes.config;

import ir.alijk.clashwarsfixes.ClashWarsFixes;

public class Config extends Configurable {
    public static boolean GENERATOR_BOOSTER_ENABLED;
    public static int GENERATOR_BOOSTER_DROP_AMOUNT;
    public static int GENERATOR_BOOSTER_DELAY;

    public static boolean GENERATOR_LIMITER_ENABLED;
    public static int GENERATOR_LIMITER_RADIUS;
    public static int GENERATOR_LIMITER_LIMIT;

    public static boolean PREVENT_BLOCK_ON_CHEST;
    public static boolean PREVENT_BLOCK_ON_ENDERCHEST;


    public Config(ClashWarsFixes instance) {
        super(instance, "config.yml");
    }

    @Override
    public void init() {
        GENERATOR_BOOSTER_ENABLED = getConfig().getBoolean("generators.booster.enabled");
        GENERATOR_BOOSTER_DROP_AMOUNT = getConfig().getInt("generators.booster.drop-amount");
        GENERATOR_BOOSTER_DELAY = getConfig().getInt("generators.booster.delay");

        GENERATOR_LIMITER_ENABLED = getConfig().getBoolean("generators.limiter.enabled");
        GENERATOR_LIMITER_RADIUS = getConfig().getInt("generators.limiter.radius");
        GENERATOR_LIMITER_LIMIT = getConfig().getInt("generators.limiter.limit");

        PREVENT_BLOCK_ON_CHEST = getConfig().getBoolean("prevent.placing-block-on-chest");
        PREVENT_BLOCK_ON_ENDERCHEST = getConfig().getBoolean("prevent.placing-block-on-enderchest");
    }
}
