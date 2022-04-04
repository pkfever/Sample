package com.tooltrip.ui.utils

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

object NavigationGraph {

/*
    * Note that setPrimaryNavigationFragment(finalHost)
    *  lets your NavHost intercept system Back button presses.
    * You can also implement this behavior in your NavHost XML by adding app:defaultNavHost="true".
    * If you're implementing custom Back button behavior and don't want your NavHost intercepting Back button presses,
    *  you can pass null to setPrimaryNavigationFragment().*/

    //For Fragment
    fun findNavController(fragment: Fragment): NavController =
        fragment.findNavController()

    //For Activity
    fun findNavController(fragmentContainerView: FragmentContainerView): NavController =
        findNavController(fragmentContainerView)

    //For View
    fun findNavController(view: View): NavController =
        findNavController(view)

    fun pushFragment(
        navController: NavController,
        direction: Int,
        bundle: Bundle? = null,
        popUpTo: Int? = null,
        popUpToInclusive: Boolean = false,
        animation: ANIMATION = ANIMATION.NONE,
        enterAnimationType: ANIMATION_TYPE = ANIMATION_TYPE.NONE,
        exitAnimationType: ANIMATION_TYPE = ANIMATION_TYPE.NONE,
        popEnterAnimationType: ANIMATION_TYPE = ANIMATION_TYPE.NONE,
        popExitAnimationType: ANIMATION_TYPE = ANIMATION_TYPE.NONE,

        ) {
        try {
            var navOptions: NavOptions.Builder? = null
            popUpTo?.let {
                navOptions = NavOptions.Builder()
                    .setPopUpTo(it, popUpToInclusive)

            }
            if (animation == ANIMATION.SIMPLE) {
                setSimpleAnimation(
                    navOptions,
                    enterAnimationType,
                    exitAnimationType,
                    popEnterAnimationType,
                    popExitAnimationType
                )
            }
            navController.navigate(direction, bundle, navOptions?.build())
        } catch (e: IllegalArgumentException) {
            e.localizedMessage ?: e.message?.let { Log.e("ExceptionNavGraph", it) }
        }

    }

    private fun setSimpleAnimation(
        navOptionsBuilder: NavOptions.Builder?,
        enterAnimationType: ANIMATION_TYPE,
        exitAnimationType: ANIMATION_TYPE,
        popEnterAnimationType: ANIMATION_TYPE,
        popExitAnimationType: ANIMATION_TYPE,
    ) {
        /*  if (enterAnimationType == ANIMATION_TYPE.SLIDE_IN_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setEnterAnim(R.anim.slide_in_right_to_left)
          } else if (enterAnimationType == ANIMATION_TYPE.SLIDE_OUT_LEFT_TO_RIGHT) {
              navOptionsBuilder?.setEnterAnim(R.anim.slide_out_left_to_right)
          } else if (enterAnimationType == ANIMATION_TYPE.SLIDE_OUT_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setEnterAnim(R.anim.slide_out_right_to_left)
          } else if (enterAnimationType == ANIMATION_TYPE.NOTHING) {
              navOptionsBuilder?.setEnterAnim(R.anim.nothing)
          }
          if (exitAnimationType == ANIMATION_TYPE.SLIDE_IN_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setExitAnim(R.anim.slide_in_right_to_left)
          } else if (exitAnimationType == ANIMATION_TYPE.SLIDE_OUT_LEFT_TO_RIGHT) {
              navOptionsBuilder?.setExitAnim(R.anim.slide_out_left_to_right)
          } else if (exitAnimationType == ANIMATION_TYPE.SLIDE_OUT_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setExitAnim(R.anim.slide_out_right_to_left)
          } else if (exitAnimationType == ANIMATION_TYPE.NOTHING) {
              navOptionsBuilder?.setExitAnim(R.anim.nothing)
          }
          if (popEnterAnimationType == ANIMATION_TYPE.SLIDE_IN_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setPopEnterAnim(R.anim.slide_in_right_to_left)
          } else if (popEnterAnimationType == ANIMATION_TYPE.SLIDE_OUT_LEFT_TO_RIGHT) {
              navOptionsBuilder?.setPopEnterAnim(R.anim.slide_out_left_to_right)
          } else if (popEnterAnimationType == ANIMATION_TYPE.SLIDE_OUT_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setPopEnterAnim(R.anim.slide_out_right_to_left)
          } else if (popEnterAnimationType == ANIMATION_TYPE.NOTHING) {
              navOptionsBuilder?.setPopEnterAnim(R.anim.nothing)
          }
          if (popExitAnimationType == ANIMATION_TYPE.SLIDE_IN_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setPopExitAnim(R.anim.slide_in_right_to_left)
          } else if (popExitAnimationType == ANIMATION_TYPE.SLIDE_OUT_LEFT_TO_RIGHT) {
              navOptionsBuilder?.setPopExitAnim(R.anim.slide_out_left_to_right)
          } else if (popExitAnimationType == ANIMATION_TYPE.SLIDE_OUT_RIGHT_TO_LEFT) {
              navOptionsBuilder?.setPopExitAnim(R.anim.slide_out_right_to_left)
          } else if (popExitAnimationType == ANIMATION_TYPE.NOTHING) {
              navOptionsBuilder?.setPopExitAnim(R.anim.nothing)
          }*/

    }

    fun <T> getFragmentResult(
        activity: FragmentActivity,
        navController: NavController,
        key: String,
        kFun: (params: T) -> Unit
    ) {

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(activity) {
                kFun(it)
            }
    }

    fun setFragmentResult(navController: NavController, key: String, value: Any) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            key,
            value
        )
        navController.popBackStack()
    }

    enum class ANIMATION {
        NONE,
        SIMPLE,
        MATERIAL
    }

    enum class ANIMATION_TYPE {
        NONE,
        SLIDE_IN_RIGHT_TO_LEFT,
        SLIDE_OUT_RIGHT_TO_LEFT,
        SLIDE_OUT_LEFT_TO_RIGHT,
        NOTHING
    }
}