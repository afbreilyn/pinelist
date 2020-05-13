import com.moowork.gradle.node.npm.NpmTask

plugins {
	id("com.github.node-gradle.node") version "2.2.0"
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

tasks.register<Copy>("copyNpmBuild") {
	//dependsOn(":web:build")
	from("${projectDir}/../web/build")
	into("src/main/resources/static")
}

tasks.assemble {
	dependsOn("copyNpmBuild")
	mustRunAfter("copyNpmBuild")
}

tasks.clean {
	delete(fileTree("src/main/resources/static/"))
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":web-adapter"))

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}



//node {
//	version = "13.6.0"
//	npmVersion = "6.13.4"
//	download = true
//}

val webDir = "${projectDir}/../web"

//tasks.named<NpmTask>("npm_run_build") {
//	inputs.files(fileTree("${webDir}/public"))
//	inputs.files(fileTree("src"))
//	inputs.file("package.json")
//	inputs.file("package-lock.json")
//
//	outputs.dir("build")
//}
//
//tasks.assemble {
//	dependsOn("npm_run_build")
//}
//
//val testsExecutedMarkerName: String = "${projectDir}/.tests.executed"
//
//tasks.registering(NpmTask::class) {
//	dependsOn("assemble")
//
//	setEnvironment(mapOf("CI" to "true"))
//	setArgs(listOf("run", "test"))
//
//	inputs.files(fileTree("src"))
//	inputs.file("package.json")
//	inputs.file("package-lock.json")
//
//	doLast {
//		File(testsExecutedMarkerName).appendText("delete this file to force re-execution JavaScript tests")
//	}
//	outputs.file(testsExecutedMarkerName)
//}
//
//tasks.check {
//	dependsOn(test)
//}
//
//tasks.clean {
//	delete(testsExecutedMarkerName)
//}
