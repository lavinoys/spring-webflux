이 저장소의 build.gradle.kts 파일은 Spring Boot Webflux 애플리케이션용 Gradle 빌드 스크립트입니다. 프로젝트 구성, 종속성 및 빌드 작업을 정의합니다.

plugins 블록은 Spring Boot 및 Kotlin 플러그인을 포함하여 프로젝트에 필요한 Gradle 플러그인을 정의합니다. group 및 version 속성은 각각 프로젝트 그룹 ID와 버전을 설정합니다.

repositories 블록은 기본 리포지토리인 mavenCentral()을 사용하여 종속성을 다운로드합니다.

dependencies 블록은 Spring Boot를 포함하여 프로젝트의 런타임 및 테스트 종속성을 나열합니다. Webflux 스타터, Kotlin 런타임 및 H2 인메모리 데이터베이스. 특정 기능이나 라이브러리를 지원하기 위해 추가 종속성을 추가할 수 있습니다.

tasks 블록은 compileKotlin, test를 비롯한 사용자 지정 빌드 작업을 정의합니다. code> 및 bootRun.

마지막으로 bootJar 및 bootRun 작업은 Spring Boot를 빌드하고 실행하도록 구성됩니다. 

이 build.gradle.kts 파일은 Spring Boot Webflux 애플리케이션을 빌드하고 배포하기 위한 견고한 기반을 제공하며 특정 프로젝트 요구 사항을 충족하도록 쉽게 사용자 지정할 수 있습니다. 
