package com.anirudha.customeui;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Gradient {

    public static GradientDrawable createGradientDrawable1(Context context) {
        int startColor = Color.parseColor("#F4401A");
        int endColor = Color.parseColor("#F7D1C9");
        float cornerRadius = context.getResources().getDimension(R.dimen.corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColors(new int[]{startColor, endColor});
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

        // Set corner radius
        gradientDrawable.setCornerRadii(new float[]{
                cornerRadius, cornerRadius,  // Top-left corner
                cornerRadius, cornerRadius,  // Top-right corner
                cornerRadius, cornerRadius,  // Bottom-right corner
                cornerRadius, cornerRadius   // Bottom-left corner
        });

        return gradientDrawable;
    }

    public static Drawable createGradientDrawable2(Context context) {
        int redColor = Color.RED;
        int whiteColor = Color.WHITE;

        GradientDrawable gradientDrawable = new GradientDrawable();
        // Top-left to top-right gradient
        gradientDrawable.setOrientation(GradientDrawable.Orientation.TL_BR);
        gradientDrawable.setColors(new int[]{redColor, whiteColor});
        // Bottom-left to bottom-right gradient
        ShapeDrawable bottomGradient = new ShapeDrawable();
        bottomGradient.setShape(new RectShape());
        bottomGradient.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public android.graphics.Shader resize(int width, int height) {
                return new android.graphics.LinearGradient(
                        0, height, width, 0,
                        new int[]{redColor, whiteColor},
                        null,
                        android.graphics.Shader.TileMode.REPEAT
                );
            }
        });
        // Combine the two gradients with a LayerDrawable
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, bottomGradient});
        layerDrawable.setLayerInset(1, 0, 0, 0, 0);
        return layerDrawable;
    }


    public static Drawable createGradientDrawable3(Context context) {

        // Set gradient background programmatically for btn

        return new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{Color.BLUE, Color.BLACK}
        );
       // btnGradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.btn_view_corner_radius));
    }

}
