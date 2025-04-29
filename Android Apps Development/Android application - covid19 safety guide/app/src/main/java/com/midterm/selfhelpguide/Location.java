package com.midterm.selfhelpguide;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

import android.content.Context;
import android.location.LocationManager;

public class Location {

    public static class Point implements Parcelable {

        public final double latitude;
        public final double longitude;

        public Point(double lat, double lon) {
            latitude = lat;
            longitude = lon;
        }

        @Override
        public String toString() {
            return "(" + latitude + ", " + longitude + ")";
        }

        public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>() {

            @Override
            public Point createFromParcel(Parcel in) {
                return new Point(in);
            }

            @Override
            public Point[] newArray(int size) {
                return new Point[size];
            }

        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeDouble(latitude);
            out.writeDouble(longitude);
        }

        private Point(Parcel in) {
            latitude = in.readDouble();
            longitude = in.readDouble();
        }

    }

    public static interface Listener {
        public void onPositionChanged();

    }

    private static final String PROVIDER_COARSE = LocationManager.NETWORK_PROVIDER;
    private static final String PROVIDER_FINE = LocationManager.GPS_PROVIDER;
    private static final String PROVIDER_FINE_PASSIVE = LocationManager.PASSIVE_PROVIDER;
    private static final long INTERVAL_DEFAULT = 10 * 60 * 1000;
    private static final float KILOMETER_TO_METER = 1000.0f;
    private static final float LATITUDE_TO_KILOMETER = 111.133f;
    private static final float LONGITUDE_TO_KILOMETER_AT_ZERO_LATITUDE = 111.320f;
    private static final Random mRandom = new Random();
    private static final double SQUARE_ROOT_TWO = Math.sqrt(2);
    private static android.location.Location mCachedPosition;
    private final LocationManager mLocationManager;
    private final boolean mRequireFine;
    private final boolean mPassive;
    private final long mInterval;
    private final boolean mRequireNewLocation;
    private int mBlurRadius;
    private android.location.Location mPosition;

    public Location(final LiveMap context) {
        this(context.getContext(), false);
    }

    public Location(final Context context, final boolean requireFine) {
        this(context, requireFine, false);
    }

    public Location(final Context context, final boolean requireFine, final boolean passive) {
        this(context, requireFine, passive, INTERVAL_DEFAULT);
    }

    public Location(final Context context, final boolean requireFine, final boolean passive, final long interval) {
        this(context, requireFine, passive, interval, false);
    }

    public Location(final Context context, final boolean requireFine, final boolean passive, final long interval, final boolean requireNewLocation) {
        mLocationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        mRequireFine = requireFine;
        mPassive = passive;
        mInterval = interval;
        mRequireNewLocation = requireNewLocation;

        if (!mRequireNewLocation) {
            mPosition = getCachedPosition();
            cachePosition();
        }
    }


    private boolean hasLocationEnabled(final String providerName) {
        try {
            return mLocationManager.isProviderEnabled(providerName);
        } catch (Exception e) {
            return false;
        }
    }

    private android.location.Location blurWithRadius(final android.location.Location originalLocation) {
        if (mBlurRadius <= 0) {
            return originalLocation;
        } else {
            android.location.Location newLocation = new android.location.Location(originalLocation);

            double blurMeterLong = calculateRandomOffset(mBlurRadius) / SQUARE_ROOT_TWO;
            double blurMeterLat = calculateRandomOffset(mBlurRadius) / SQUARE_ROOT_TWO;

            newLocation.setLongitude(newLocation.getLongitude() + meterToLongitude(blurMeterLong, newLocation.getLatitude()));
            newLocation.setLatitude(newLocation.getLatitude() + meterToLatitude(blurMeterLat));

            return newLocation;
        }
    }

    private static int calculateRandomOffset(final int radius) {
        return mRandom.nextInt((radius + 1) * 2) - radius;
    }

    public double getLatitude() {
        if (mPosition == null) {
            return 0.0f;
        } else {
            android.location.Location position = blurWithRadius(mPosition);
            return position.getLatitude();
        }
    }

    public double getLongitude() {
        if (mPosition == null) {
            return 0.0f;
        } else {
            android.location.Location position = blurWithRadius(mPosition);
            return position.getLongitude();
        }
    }

    private String getProviderName() {
        return getProviderName(mRequireFine);
    }

    private String getProviderName(final boolean requireFine) {
        if (requireFine) {

            if (mPassive) {
                return PROVIDER_FINE_PASSIVE;
            } else {
                return PROVIDER_FINE;
            }
        } else {
            if (hasLocationEnabled(PROVIDER_COARSE)) {
                if (mPassive) {
                    throw new RuntimeException("ERROR");
                } else {
                    return PROVIDER_COARSE;
                }
            } else {
                if (hasLocationEnabled(PROVIDER_FINE) || hasLocationEnabled(PROVIDER_FINE_PASSIVE)) {
                    return getProviderName(true);
                } else {

                    return PROVIDER_COARSE;
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private android.location.Location getCachedPosition() {
        if (mCachedPosition != null) {
            return mCachedPosition;
        } else {
            try {
                return mLocationManager.getLastKnownLocation(getProviderName());
            } catch (Exception e) {
                return null;
            }
        }
    }

    private void cachePosition() {
        if (mPosition != null) {
            mCachedPosition = mPosition;
        }
    }

    public static double latitudeToKilometer(double latitude) {
        return latitude * LATITUDE_TO_KILOMETER;
    }

    public static double latitudeToMeter(double latitude) {
        return latitudeToKilometer(latitude) * KILOMETER_TO_METER;
    }

    public static double meterToLatitude(double meter) {
        return meter / latitudeToMeter(1.0f);
    }

    public static double longitudeToKilometer(double longitude, double latitude) {
        return longitude * LONGITUDE_TO_KILOMETER_AT_ZERO_LATITUDE * Math.cos(Math.toRadians(latitude));
    }

    public static double longitudeToMeter(double longitude, double latitude) {
        return longitudeToKilometer(longitude, latitude) * KILOMETER_TO_METER;
    }

    public static double meterToLongitude(double meter, double latitude) {
        return meter / longitudeToMeter(1.0f, latitude);
    }
}