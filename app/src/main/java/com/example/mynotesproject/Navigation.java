package com.example.mynotesproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Navigation {
    private final FragmentManager fragmentManager;

    public Navigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment,int container, boolean useBackstack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container,fragment);
        if (useBackstack){
            transaction.addToBackStack(null);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
