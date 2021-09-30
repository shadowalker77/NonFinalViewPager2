# NonFinalViewPager2

ViewPager2 widget but non **final**.

# Project overview

ViewPager2 is a very helpful widget which brings a lot to us. But unfortunately its class in final! So any further development and extra feature would be highly limited. This library aims to have a ViewPager2 which is not final and made it possible to override and impelement extra features. Just use NonFinalViewPager2 class instead of ViewPager2 and take care: you **SHOULD** extend your adapters from library package, not the adapters from ViewPager2 package.

# Adding to project

Add jitpack maven to project level gradle:
```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then add below dependency and simply use NonFinalViewPager2 class:

```java
implementation 'com.github.shadowalker77:nonfinalviewpager2:0.2.1'
```

If you had any question, please do not hesitate to contact me: alireza.bdn@gmail.com
