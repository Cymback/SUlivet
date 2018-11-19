package com.example.sulivet.sulivet.bottomnavigation.helper

import android.support.v4.app.Fragment
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.bottomnavigation.ui.HomeFragment
import com.example.sulivet.sulivet.bottomnavigation.ui.MapFragment
import com.example.sulivet.sulivet.bottomnavigation.ui.NotificationsFragment
import com.example.sulivet.sulivet.bottomnavigation.ui.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {

    // VALUES TO MENU.XML
    HOME(0, R.id.home),
    MAP(1, R.id.map),
    NOTIFICATIONS(2, R.id.notifications),
    PROFILE(3, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.MAP.id -> BottomNavigationPosition.MAP
    BottomNavigationPosition.NOTIFICATIONS.id -> BottomNavigationPosition.NOTIFICATIONS
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.newInstance()
    BottomNavigationPosition.MAP -> MapFragment.newInstance()
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.MAP -> MapFragment.TAG
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}

