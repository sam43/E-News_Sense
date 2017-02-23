package com.rrmsense.enewspaperonline;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by sam43 on 2/11/17.
 */

public class AppConfig {
    private int HEIGHT;
    private int WIDTH;

    private int COLOMN;
    private int IMAGE_HEIGHT;
    private int IMAGE_WIDTH;

    private int IMAGE_CURRENT_HEIGHT;
    private int IMAGE_CURRENT_WIDTH;
    private int IMAGE_NEW_HEIGHT;
    private int IMAGE_NEW_WIDTH;

    public AppConfig() {
    }

    public int getIMAGE_NEW_HEIGHT() {
        return IMAGE_NEW_HEIGHT;
    }

    public void setIMAGE_NEW_HEIGHT(int IMAGE_NEW_HEIGHT) {
        this.IMAGE_NEW_HEIGHT = IMAGE_NEW_HEIGHT;
    }

    public int getIMAGE_NEW_WIDTH() {
        return IMAGE_NEW_WIDTH;
    }

    public void setIMAGE_NEW_WIDTH(int IMAGE_NEW_WIDTH) {
        this.IMAGE_NEW_WIDTH = IMAGE_NEW_WIDTH;
    }

    public int getIMAGE_CURRENT_WIDTH() {
        return IMAGE_CURRENT_WIDTH;
    }

    public void setIMAGE_CURRENT_WIDTH(int IMAGE_CURRENT_WIDTH) {
        this.IMAGE_CURRENT_WIDTH = IMAGE_CURRENT_WIDTH;
    }

    public int getIMAGE_CURRENT_HEIGHT() {
        return IMAGE_CURRENT_HEIGHT;
    }

    public void setIMAGE_CURRENT_HEIGHT(int IMAGE_CURRENT_HEIGHT) {
        this.IMAGE_CURRENT_HEIGHT = IMAGE_CURRENT_HEIGHT;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getCOLOMN() {
        if(COLOMN==0)
            return 1;
        return COLOMN;
    }

    public void setCOLOMN(int COLOMN) {
        this.COLOMN = COLOMN;
    }

    public int getIMAGE_HEIGHT() {
        return IMAGE_HEIGHT;
    }

    public void setIMAGE_HEIGHT(int IMAGE_HEIGHT) {
        this.IMAGE_HEIGHT = IMAGE_HEIGHT;
    }

    public int getIMAGE_WIDTH() {
        return IMAGE_WIDTH;
    }

    public void setIMAGE_WIDTH(int IMAGE_WIDTH) {
        this.IMAGE_WIDTH = IMAGE_WIDTH;
    }

    public void CalculateColumn(int  x) {
        int column = getWIDTH()/x;
        setCOLOMN(column);
    }
    public void Image_Setter() {
        int img_new_width = getWIDTH()/getCOLOMN();
        setIMAGE_NEW_WIDTH(img_new_width);
        int img_new_height = (getIMAGE_NEW_WIDTH()*getIMAGE_CURRENT_HEIGHT())/getIMAGE_CURRENT_WIDTH();
        setIMAGE_NEW_HEIGHT(img_new_height);
        Log.d("new_img_sze", "" + getIMAGE_NEW_HEIGHT()+ "," + "" + getIMAGE_NEW_WIDTH());
    }

    public void New_image_size (int curr_height, int curr_width) {
        setIMAGE_CURRENT_HEIGHT(curr_height);
        setIMAGE_CURRENT_WIDTH(curr_width);
        Image_Setter();
        Log.d("curr_img_sze", "" + getIMAGE_CURRENT_HEIGHT()+ "," + "" + getIMAGE_CURRENT_WIDTH());
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        //bm.recycle();
        return resizedBitmap;
    }
}
