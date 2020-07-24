# DataBreaker
[![JitPack](https://jitpack.io/v/SuperCoder7979/databreaker.svg)](https://jitpack.io/#SuperCoder7979/databreaker "SuperCoder7979/databreaker on JitPack")

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
		name = "JitPack"
		url = "https://jitpack.io"
	}
}
```

### 2. add this to your build script in `dependencies`:  
for `databreaker_version` use the latest commit hash.
If you don't want the latest build, you can find a list of builds on [JitPack](https://jitpack.io/#SuperCoder7979/databreaker "SuperCoder7979/databreaker on JitPack"). You can also use any git tagged version, or the (short or long) hash of any specific commit.
```gradle
modRuntime ("com.github.SuperCoder7979:databreaker:${databreaker_version}") {
	exclude module: "fabric-loader"
}
```
