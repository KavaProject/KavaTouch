/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONDeviceConfiguration {
    public float scale;
    public boolean flipYAxis;
    public boolean useIPadTheme;
    public List<String> deviceModifiers = new ArrayList<String>();
    public List<ImageScaleModifier> imageScaleModifiers = new ArrayList<ImageScaleModifier>();


    public JSONDeviceConfiguration(Resources resources, int id) {
        InputStream jsonStream = resources.openRawResource(id);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = jsonStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = jsonStream.read();
            }
            jsonStream.close();
        } catch (IOException e) {
            throw new Error(e);
        }
        try {
            JSONObject jConfiguration = new JSONObject(byteArrayOutputStream.toString());
            flipYAxis = jConfiguration.getBoolean("flipYAxis");
            useIPadTheme = jConfiguration.getBoolean("useIPadTheme");
            JSONArray jDeviceModifiers = jConfiguration.getJSONArray("deviceModifiers");
            for (int i = 0; i < jDeviceModifiers.length(); i++) {
                deviceModifiers.add(jDeviceModifiers.getString(i));
            }
            JSONArray jImageScaleModifiers = jConfiguration.getJSONArray("imageScaleModifiers");
            for (int i = 0; i < jImageScaleModifiers.length(); i++) {
                imageScaleModifiers.add(ImageScaleModifier.createFromJSON(jImageScaleModifiers.getJSONObject(i)));
            }
            double scale = jConfiguration.optDouble("scale");
            if (Double.isNaN(scale)) {
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                this.scale = displayMetrics.density;
            } else {
                this.scale = (float) scale;
            }
        } catch (JSONException e) {
            throw new Error(e);
        }
    }
}
