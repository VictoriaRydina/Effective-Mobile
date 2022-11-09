package com.example.effectivemobile.core_ui

interface Mapper<Input, Output> {
    fun map(input: Input): Output?
}