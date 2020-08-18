plugins {
    `java-library`
    signing
    `maven-publish`
    id("net.minecrell.licenser") version "0.4.1"
}

group = "com.demonwav.mcdev"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

license {
    header = file("header.txt")
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
                        id.set("DemonWav")
                        name.set("Kyle Wood")
                        email.set("demonwav@gmail.com")
                        url.set("https://github.com/DemonWav")
                    }
                }

                scm {
                    url.set(repoUrl)
                    connection.set("scm:git:$repoUrl.git")
                    developerConnection.set(connection)
                }
            }
        }

        repositories {
            val url = if (isSnapshot) {
                "https://oss.sonatype.org/content/repositories/snapshots/"
            } else {
                "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            }
            maven(url) {
                credentials(PasswordCredentials::class)
                name = "osshr"
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign> {
    onlyIf { !isSnapshot }
}
