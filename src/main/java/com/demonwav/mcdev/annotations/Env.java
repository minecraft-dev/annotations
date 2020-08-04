/*
 * Minecraft Dev for IntelliJ Annotations
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2020 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.annotations;

/**
 * An environment, or distribution, of Minecraft.
 *
 * @see net.minecraftforge.api.distmarker.Dist
 * @see net.fabricmc.api.EnvType
 */
public enum Env {
    /**
     * The Minecraft client (including the integrated server for singleplayer).
     */
    CLIENT,
    /**
     * The dedicated server.
     */
    SERVER
}
