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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to tell Minecraft Dev that a package, class, field or method should only be accessed in a certain environment,
 * the client or the dedicated server.
 *
 * <p>Unlike with its counterpart in the Minecraft source, elements marked with this annotation are not stripped at
 * runtime, this is for documentation and static analysis only.</p>
 *
 * <p>This annotation should usually be used in preference to the other one.</p>
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface CheckEnv {
    /**
     * The environment in which this element should only be accessed.
     */
    Env value();
}
