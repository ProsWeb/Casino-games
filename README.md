[![](https://jitpack.io/v/ProsWeb/project-lvl1-s499.svg)](https://jitpack.io/#ProsWeb/project-lvl1-s499)

[![Build Status](https://travis-ci.org/ProsWeb/Casino-games.svg?branch=master)](https://travis-ci.org/ProsWeb/Casino-games)

# Hexlet java project - "Casino"

### Install make

```bash
$ sudo apt-get update
$ sudo apt-get install make
```

## How use library (maven)

Step 1. Add the JitPack repository to your build file

```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
 ``` 
 	
 Step 2. Add the dependency
 
```
<dependency>
  	<groupId>com.github.ProsWeb</groupId>
  	<artifactId>project-lvl1-s499</artifactId>
  	<version>Tag</version>
</dependency>
```

### Compile and run

```bash
$ make
```

### Кратко о проекте

 - В рамках проекта реализован набор из трёх мини-игр, запускаемых из консоли.
 Эти игры взяты из казино - "однорукий бандит", "пьяница" и "очко".
 - запускается класс Choice, где в консоле выбирается игра

### Использованы инструменты
 - maven
 - travis
 - jitpack