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
 * This is analogous to {@code net.minecraftforge.api.distmarker.Dist} in Forge and {@code net.fabricmc.api.EnvType}
 * in Fabric.
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
