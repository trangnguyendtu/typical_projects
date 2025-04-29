package com.midterm.selfhelpguide;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CategoryIntro{
    public String Name, Content;
    public Integer Picture, Icon;

    public CategoryIntro(String name, String content, Integer picture, Integer icon) {
        this.Name = name;
        this.Content = content;
        this.Picture = picture;
        this.Icon = icon;
    }
}


