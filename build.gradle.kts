plugins {
    id("org.jetbrains.intellij") version "1.5.3"
    java
    kotlin("jvm") version "1.6.20"
}

group "com.asyncapi.plugin.idea"
version = "1.4.0+idea2021"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.3")
    plugins.set(listOf("yaml"))
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    sinceBuild.set("211")
    untilBuild.set("213.*")
    changeNotes.set("""
        <b>New AsyncAPI versions:</b>
        <ul>
            <li>2.1.0</li>
            <li>2.2.0</li>
            <li>2.3.0</li>
        </ul>
    """.trimIndent())
}

tasks.getByName<org.jetbrains.intellij.tasks.RunPluginVerifierTask>("runPluginVerifier") {
    ideVersions.set(listOf(
        "2021.1",
        "2021.1.1",
        "2021.1.2",
        "2021.1.3",
        "2021.2",
        "2021.2.1",
        "2021.2.2",
        "2021.2.3",
        "2021.3",
        "2021.3.1",
        "2021.3.2",
        "2021.3.3"
    ))
    verifierVersion.set("1.278")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    test {
        useJUnitPlatform()
    }
}