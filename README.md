# CustomClock(自定义时钟)



##**Introduction**
This is a simple demo to show you a custom clock in android.If you want to use it in your project,you can copy the "TimeView",and "attrs" files from this demo.Few days later,I will commit it to maven repository.

##**Style Of Clock**
There are many styles you can choose:

![](https://raw.githubusercontent.com/Lloyd0577/CustomClockForAndroid/master/img/ezgif.com-video-to-gif4.gif)

![](https://raw.githubusercontent.com/Lloyd0577/CustomClockForAndroid/master/img/ezgif.com-634513e019.gif)


![](https://raw.githubusercontent.com/Lloyd0577/CustomClockForAndroid/master/img/ezgif.com-video-to-gif2.gif)

![](https://raw.githubusercontent.com/Lloyd0577/CustomClockForAndroid/master/img/ezgif.com-video-to-gif6.gif)




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


       
       











