# Eclipse-ColorMix

Eclipse Color-Mix is an advanced library to mix/select color by mixing RGBA colors.

# Use

1. Download this project and import as Android application in eclipse ADT.

2. Add ColorMixer library to your project.

3. Now in your project:

	i. Implement the ColorSelector.ColorControl interface in your class. Implement the unimplemented methods. This will override two methods public void onCancelled() and public void onColorSelected(int color). Actually these methods are the listener methods.
	
4. Create an instance of the ColorSelector class and innitialize it. The innitialization takes two mandatory parameters, the context and the ColorControl object. Alternatively you can pass one more parameter as int. This one is the theme for the Color dialog.

##### Syntax

```
ColorSelector cs = new ColorSelector(Context context, ColorControl colorControl, int theme);
```

##### Code Example

```
ColorSelector cs = new ColorSelector(this, this, 4);
```

5. Now call the show() method on the object of ColorSelector class

##### Popping up the ColorMix

```
cs.show();
```



# Interface and Methods



### Interface

The ColorSelector class contains one interface named ColorControl. This ColorControl interface contains following methods:



##### public void onCancelled()

The method gets called when the user cancels the ColorSelector dialog. Can be used to free resources.


##### public void onColorSelected(int color)

The method gets called when the user selects a color. The parameter 'int color' contains the color selected by the user. This color can now be used in your application. 



### Methods

The ColorSelector class contains several methods to control the look, feel and features of the color mixer dialog. Here are the methods:



##### public void setColor(int hex)

Takes a pareameter as a color of Color class or an integer color and sets the innitial color value to it. The method must be called before the show() method.



##### public void setARGBColor(int alpha, int red, int green, int blue)

Takes 4 pareameters as integers and sets the innitial color value to it. The values of these integers could be 0-255 and are alpha, red, green and blue respectively. The method must be called before the show() method.



##### public void setRGBColor(int red, int green, int blue)

Same as setARGBColor method without an alpha channel. 



##### public void supportsAlpha(boolean alpha)

Takes a boolean parameter to specify whether it supports alpha channel.



##### public void colorPreview(boolean color_preview)

Takes a boolean parameter to specify whether color preview should be displayed.



##### public void showLEDS(boolean advanced_ui)

Takes a boolean parameter to specify whether color LEDs should be displayed.







