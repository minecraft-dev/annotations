/*
 * Minecraft Dev for IntelliJ Annotations
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2023 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to tell MinecraftDev that a {@code String} parameter to a method should point to an entry in the mod's
 * translation files. If there is an {@code Object} varargs parameter to that method, it is assumed that those are the
 * arguments to this translation.
 *
 * <p>Example:
 * <pre>
 * {@code
 * public static Component errorMessage(@Translatable String translationKey, Object... args) {
 *     return Component.translatable(translationKey, args).styled(style -> style.withColor(Formatting.RED));
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
@Documented
public @interface Translatable {
    /**
     * Defaults to {@code true}. If this value is {@code false}, MinecraftDev will not create a warning if the
     * translation key doesn't point to a valid translation. This is intended for use when a {@code String} <i>maybe</i>
     * points to a translation, but not necessarily.
     */
    boolean required() default true;

    /**
     * Whether to fold the entire method call. Defaults to {@code false}.
     */
    boolean foldMethod() default false;

    /**
     * The prefix that will be added to this argument.
     *
     * <p>Example:
     * <pre>
     * {@code
     * public static Component itemTranslatable(@Translatable(prefix = "item.") String translationKey) {
     *     return Component.translatable("item." + translationKey);
     * }
     * }
     * </pre>
     */
    String prefix() default "";

    /**
     * The suffix that will be added to this argument.
     *
     * <p>Example:
     * <pre>
     * {@code
     * public static Component translatableWithSuffix(@Translatable(prefix = ".suffix") String translationKey) {
     *     return Component.translatable(translationKey + ".suffix");
     * }
     * }
     * </pre>
     */
    String suffix() default "";
}
