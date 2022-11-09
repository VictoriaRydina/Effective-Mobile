package com.example.effectivemobile.core_ui.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output?
}