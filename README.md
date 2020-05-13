# DataBreaker

this is a very cused mod  
it makes mc load way faster (on server and client) by removing DataFixerUpper  
i'm not responsible if you completely screw up your world  
thanks, have a great day  

## how to add this mod to your dev env
if you have the inclination to add databreaker to your dev env, follow these 3 easy steps:  
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
3. cry
