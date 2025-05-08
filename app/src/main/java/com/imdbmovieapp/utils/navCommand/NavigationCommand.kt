package com.imdbmovieapp.utils.navCommand

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirection(val fragment: Fragment) : NavigationCommand()
    object Back : NavigationCommand()
}