package com.amonmcduul.moviesinthepocket.Utilities;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * The type Prefs.
 */
public class Prefs {
    /**
     * The Shared preferences.
     */
    SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Prefs.
     *
     * @param activity the activity
     */
    public Prefs(Activity activity) {
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    /**
     * Sets search.
     *
     * @param search the search
     */
    public void setSearch(String search) {
        sharedPreferences.edit().putString("search", search).commit();
    }

    /**
     * Gets search.
     *
     * @return the search
     */
    public String getSearch() {
        return sharedPreferences.getString("Search", "Star Trek");
    }
}
