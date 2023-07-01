package org.zaksen.enchframe;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantedFrames implements ModInitializer
{
    public static final String MOD_ID = "enchframe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize()
    {
        LOGGER.info("Start initialization...");
    }
}
