# DataBreaker
![Maven Version](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fmaven.gegy.dev%2Fsupercoder79%2Fdatabreaker%2Fmaven-metadata.xml)

DataBreaker speeds up your minecraft loading time by stopping the execution of DataFixerUpper.
As you can imagine this is very hacky and stops world backwards compatibility. This tool is intended for MOD DEVS ONLY.
Do not use it in a normal Minecraft environment. Even if you try it will just crash you anyway.
<br/>
<br/>
## How to add this mod to your development environment
If you have the inclination to add databreaker to your dev env, follow these 2 easy steps:  

### 1. add this to your build script before `dependencies`:  
:exclamation:Note: do ***NOT*** put it in the `publishing` section
```gradle
repositories {
	maven {
		name = "Gegy"
		url = "https://maven.gegy.dev"
	}
}
```

### 2. add this to your build script in `dependencies`:  
For `databreaker_version` use the latest commit hash.
If you don't want the latest build, you can find a list of builds on [Maven](https://maven.gegy.dev/supercoder79/databreaker/). You can also use any git tagged version, or the (short or long) hash of any specific commit.
```gradle
modRuntime ("SuperCoder7979:databreaker:${databreaker_version}") {
	exclude module: "fabric-loader"
}
```
