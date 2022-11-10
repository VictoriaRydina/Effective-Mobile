package com.example.effectivemobile.di.deps

import com.example.effectivemobile.main.di.deps.MainDeps
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps

interface AppDeps :
    DetailsProductDeps,
    MainDeps