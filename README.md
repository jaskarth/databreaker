# DataBreaker

DataBreaker speeds up your minecraft loading time by stopping the execution of DataFixerUpper.
As you can imagine this is very hacky and stops world backwards compatibility. This tool is intended for MOD DEVS ONLY.
Do not use it in a normal Minecraft environment. Even if you try it will just crash you anyway.

## how to add this mod to your dev env
If you have the inclination to add databreaker to your dev env, follow these 3 easy steps:  
1. add this to your build script before `dependencies`
```
repositories {
	maven { url = "https://jitpack.io" }
}
```
2. add this to your build script in `dependencies`:
```
modRuntime ("com.github.SuperCoder7979:databreaker:<whatever the latest commit hash is>") {
	exclude module : "fabric-loader"
}
```
You can also use the latest git tagged version.
