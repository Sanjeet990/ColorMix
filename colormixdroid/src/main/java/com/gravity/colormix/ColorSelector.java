package com.gravity.colormix;

/**
 * Created by Sanjeet on 1/4/2018.
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

@SuppressLint("NewApi")
public class ColorSelector extends AlertDialog.Builder implements SeekBar.OnSeekBarChangeListener {

    public interface ColorControl {
        public void onCancelled();

        public void onColorSelected(int color);
    }

    Context context;

    ColorControl colorControl;

    SeekBar seek_red, seek_green, seek_blue, seek_alpha;
    private int red = 0, green = 0, blue = 0, alpha = 255, i_alpha = 255;
    LinearLayout display, _color_preview, alpha_container;

    private int color = 0xff000000;
    private boolean supports_alpha = false, color_preview = true, advanced_ui = true;

    public ColorSelector(Context context, ColorControl colorControl) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.colorControl = colorControl;
    }


    public ColorSelector(Context context, ColorControl colorControl, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.colorControl = colorControl;
    }


    public ColorSelector(Context context, ColorControl colorControl, boolean alpha) {
        super(context);
        // TODO Auto-generated constructor stub
        // setTitle("Pick a color");
        this.context = context;
        this.colorControl = colorControl;
        this.supports_alpha = alpha;
    }

    public ColorSelector(Context context, ColorControl colorControl, boolean alpha, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
        // setTitle("Pick a color");
        this.context = context;
        this.colorControl = colorControl;
        this.supports_alpha = alpha;
    }

    @Override
    public AlertDialog create() {
        int layout = 0;
        if (advanced_ui) layout = R.layout.led_colorpallate;
        else layout = R.layout.colorpallate;

        LayoutInflater li = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(layout, null);
        // LinearLayout v = new LinearLayout(context);
        setView(v);

        // Setup controls
        seek_red = (SeekBar) v.findViewById(R.id.seek_red);
        seek_green = (SeekBar) v.findViewById(R.id.seek_green);
        seek_blue = (SeekBar) v.findViewById(R.id.seek_blue);
        seek_alpha = (SeekBar) v.findViewById(R.id.seek_alpha);
        display = (LinearLayout) v.findViewById(R.id.color_display);
        _color_preview = (LinearLayout) v.findViewById(R.id.color_preview);
        alpha_container = (LinearLayout) v.findViewById(R.id.alpha_container);

        //if alpha enabled
        if (supports_alpha) {
            alpha_container.setVisibility(View.VISIBLE);
            alpha = i_alpha;
            seek_alpha.setOnSeekBarChangeListener(this);
        } else {
            alpha_container.setVisibility(View.GONE);
        }

        //enable/disable preview of color
        if (color_preview) _color_preview.setVisibility(View.VISIBLE);

        //Set innitial values
        color = ColorHelper.getHex(alpha, red, green, blue);
        display.setBackgroundColor(color);
        seek_alpha.setProgress(alpha);
        seek_red.setProgress(red);
        seek_green.setProgress(green);
        seek_blue.setProgress(blue);


        //// Set seekChangeListeners
        seek_red.setOnSeekBarChangeListener(this);
        seek_green.setOnSeekBarChangeListener(this);
        seek_blue.setOnSeekBarChangeListener(this);


        ////Set buttons
        this.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                if (colorControl != null) colorControl.onCancelled();
            }
        });

        this.setPositiveButton("Select", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                if (colorControl != null) colorControl.onColorSelected(color);
            }
        });

        this.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                // TODO Auto-generated method stub
                colorControl.onCancelled();
            }
        });

        //Prevent adding custom Neutral Button
        this.setNeutralButton(null, null);

        return super.create();
    }

    @Override
    public void onProgressChanged(SeekBar bar, int value, boolean arg2) {
        // TODO Auto-generated method stub
        int id = bar.getId();
        if (id == R.id.seek_red) {
            red = value;
        } else if (id == R.id.seek_green) {
            green = value;
        } else if (id == R.id.seek_blue) {
            blue = value;
        } else if (id == R.id.seek_alpha) {
            alpha = value;
        }
        color = ColorHelper.getHex(alpha, red, green, blue);
        display.setBackgroundColor(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub
    }

    public void setColor(int hex) {
        ColorHelper color_list = ColorHelper.getARGB(hex);

        this.i_alpha = color_list.val_alpha;
        this.red = color_list.val_red;
        this.green = color_list.val_green;
        this.blue = color_list.val_blue;
    }

    public void setARGBColor(int alpha, int red, int green, int blue) {
        this.i_alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setRGBColor(int r, int g, int b) {
        int hex = ColorHelper.getHex(alpha, r, g, b);
        color = hex;
    }

    public void supportsAlpha(boolean alpha) {
        this.supports_alpha = alpha;
    }


    public void colorPreview(boolean color_preview) {
        // TODO Auto-generated method stub
        this.color_preview = color_preview;
    }


    public void showLEDS(boolean advanced_ui) {
        // TODO Auto-generated method stub
        this.advanced_ui = advanced_ui;
    }


    private static class ColorHelper {

        public int val_alpha = 0;
        public int val_red = 0;
        public int val_green = 0;
        public int val_blue = 0;

        public static int getHex(int a, int r, int g, int b) {
            String redx = String.format("%2s", Integer.toHexString(r)).replace(' ', '0');
            ;
            String greenx = String.format("%2s", Integer.toHexString(g)).replace(' ', '0');
            ;
            String bluex = String.format("%2s", Integer.toHexString(b)).replace(' ', '0');
            ;
            String alphax = String.format("%2s", Integer.toHexString(a)).replace(' ', '0');

            String clr = "#" + alphax + redx + greenx + bluex;
            return Color.parseColor(clr);
        }

        public static ColorHelper getARGB(int hex) {
            // TODO Auto-generated method stub
            ColorHelper color = new ColorHelper();

            String val = Integer.toHexString(hex);

            if (val.length() < 6) {
                val = String.format("%-6s", val).replace(' ', '0');
            }

            if (val.length() < 8) {
                val = "ff" + val;
            }


            final int[] ret = new int[4];
            for (int i = 0; i < 4; i++) {
                ret[i] = Integer.parseInt(val.substring(i * 2, i * 2 + 2), 16);
            }

            color.val_alpha = ret[0];
            color.val_red = ret[1];
            color.val_green = ret[2];
            color.val_blue = ret[3];
            return color;
        }

    }
}
