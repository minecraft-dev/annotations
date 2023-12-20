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
     * @return {@code true} if a translation key must point to a valid translation.
     */
    boolean required() default true;

    /**
     * Whether to fold the entire method call. Defaults to {@code false}.
     * @return {@code true} to enable folding the entire method call.
     */
    boolean foldMethod() default false;

    /**
     * Whether arbitrary argument types are allowed in the translation argument list, if present. If false, the argument
     * list must only contain instances of {@linkplain String}, {@linkplain Number}, {@linkplain Boolean} and
     * {@code Component}. If true, other argument types are allowed and are converted to strings as if by using
     * {@linkplain String#valueOf}.
     *
     * <p>If this annotation is used on a class within the {@code net.minecraft} package, and the current Minecraft
     * version is less than 23w40a (a snapshot for 1.20.3), then MinecraftDev behaves as if this value is true.
     */
    boolean allowArbitraryArgs() default false;

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
     * @return The prefix that will be added to this argument.
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
     * @return The suffix that will be added to this argument.
     */
    String suffix() default "";
}
