package com.example.effectivemobile.core_ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Retention
@MapKey
annotation class VMKey(val value: KClass<out ViewModel>)