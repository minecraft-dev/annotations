plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("org.cadixdev.licenser") version "0.6.1"
}

group = "com.demonwav.mcdev"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 8
}

license {
    header(file("header.txt"))
}

val isSnapshot = version.toString().endsWith("-SNAPSHOT")

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
            withoutBuildIdentifier()

            pom {
                val repoUrl = "https://github.com/minecraft-dev/annotations"

                name.set("Minecraft Dev Annotations")
                description.set("Annotations for the Minecraft Development for IntelliJ plugin to assist with static code analysis.")
                url.set(repoUrl)
                inceptionYear.set("2020")
                packaging = "jar"

                licenses {
                    license {
                        name.set("MIT")
                        url.set("$repoUrl/blob/master/license.txt")
                        distribution.set("repo")
                    }
                }

                issueManagement {
                    system.set("GitHub")
                    url.set("$repoUrl/issues")
                }

                developers {
                    developer {
                        id.set("DenWav")
                        name.set("Kyle Wood")
                        email.set("kyle@denwav.dev")
                        url.set("https://github.com/DenWav")
                    }
                }

                scm {
                    url.set(repoUrl)
                    connection.set("scm:git:$repoUrl.git")
                    developerConnection.set(connection)
                }
            }
        }
    }
}

nexusPublishing.repositories {
    sonatype()
}

// Don't configure signing unless this is present
val sonatypeUsername: Provider<String> = providers.gradleProperty("sonatypeUsername")
val sonatypePassword: Provider<String> = providers.gradleProperty("sonatypePassword")

val gpgSigningKey: Provider<String> = providers.environmentVariable("GPG_SIGNING_KEY")
val gpgPassphrase: Provider<String> = providers.environmentVariable("GPG_PASSPHRASE")

if (sonatypeUsername.isPresent && sonatypePassword.isPresent) {
    signing {
        setRequired {
            !isSnapshot && gradle.taskGraph.hasTask("publishToSonatype")
        }

        if (gpgSigningKey.isPresent && gpgPassphrase.isPresent) {
            useInMemoryPgpKeys(gpgSigningKey.get(), gpgPassphrase.get())
        } else {
            useGpgCmd()
        }

        sign(publishing.publications["mavenJava"])
    }
}
