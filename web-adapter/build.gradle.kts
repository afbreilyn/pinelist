import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
//	id("spring-cloud-contract")
//	id("io.spring.cloud")
//	id("io.springframework.cloud")
//	id("org.springframework.cloud")
//	id("org.springframework.cloud.contract") version "2.2.2.RELEASE"
}
//
//buildscript {
//	repositories {
//		// ...
//	}
//	dependencies {
//		classpath("org.springframework.cloud:spring-cloud-contract-gradle-plugin:${property("springCloudVersion")}")
//		classpath("org.springframework.cloud:spring-cloud-contract-spec-kotlin:${property("springCloudVersion")}")
//	}
//}

buildscript {
	repositories {
		maven {
			url = uri("https://plugins.gradle.org/m2/")
		}
	}
	dependencies {
		classpath("gradle.plugin.org.springframework.cloud:spring-cloud-contract-gradle-plugin:2.2.2.RELEASE")
		classpath("org.springframework.cloud:spring-cloud-contract-spec-kotlin:2.2.2.RELEASE")
	}
}

apply(plugin = "org.springframework.cloud.contract")


dependencyManagement {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
//		mavenBom("org.springframework.cloud:spring-cloud-contract-spec-kotlin:${property("springCloudVersion")}")
	}
}

dependencies {
	implementation(project(":list-policy"))

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	testImplementation("io.rest-assured:spring-web-test-client")

	implementation("org.springframework.cloud:spring-cloud-contract-gradle-plugin:${property("springCloudVersion")}")
	implementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin:${property("springCloudVersion")}")
	implementation("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR3")

	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
	testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
}

//contracts {
//	packageWithBaseClasses = 'com.example'
//	baseClassMappings {
//		baseClassMapping(".*intoxication.*", "com.example.intoxication.BeerIntoxicationBase")
//	}
//}

org.springframework.cloud.contract.spec.ContractDsl.contract {
	packageWithBaseClasses = 'com.example'
	baseClassMappings {
		baseClassMapping(".*intoxication.*", "com.example.intoxication.BeerIntoxicationBase")
	}
}