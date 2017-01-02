# CustomClock(自定义时钟)



##**Introduction**
This is a simple demo to show you a custom clock in android.If you want to use it in your project,you can copy the "TimeView",and "attrs" files from this demo.Few days later,I will commit it to maven repository.

##**Style Of Clock**
There are many styles you can choose:

![](http://upload-images.jianshu.io/upload_images/3057657-6ff84adda685b0bb.gif?imageMogr2/auto-orient/strip)

![](http://upload-images.jianshu.io/upload_images/3057657-0024bba74826681b.gif?imageMogr2/auto-orient/strip)


![](http://upload-images.jianshu.io/upload_images/3057657-0f7fc90871df161d.gif?imageMogr2/auto-orient/strip)

![](http://upload-images.jianshu.io/upload_images/3057657-1832f872d2d9eea5.gif?imageMogr2/auto-orient/strip)




##**How To Use**

**1 It provides 26  properties you can set in your xml file.**

- borderWidth 
- borderColor 
- circleBackground 
- minScaleColor 
- midScaleColor
- maxScaleColor
- minScaleLength
- midScaleLength
- maxScaleLength
- textColor
- textSize
- isDrawText
- secondPointerColor
- minPointerColor
- hourPointerColor
- secondPointerLength
- minPointerLength
- hourPointerLength
- secondPointerSize
- minPointerSize
- hourPointerSize
- centerPointColor
- centerPointSize
- centerPointRadiu 
- centerPointType  
- isSecondGoSmooth 

I believe that those properties are easy to use by names.Second pointer has two status,you can set "isSecondGoSmooth" to make it move smoothly or not.Also there are two shape for you to set center shapes of view:"circle" and "rect"

**2 It provides several methods you use directly:**

> ####void setTime(int hour, int min)
  

 Set hours and minutes;
  
> ####void setTime(int hour, int min,int second)

Set hour、minutes and seconds

> ####int getHour()

Return current hours;

> ####int getMin()

Return current minutes;

>####int getSecond()

Return current seconds;

>#### void start()

Start the clock.If you do not want it move,you needn't call this method.


       
       











