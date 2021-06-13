package com.prof18.filmatic.core.architecture

/**
 * UseCase that returns a value when invoked
 */
interface UseCase<T> {
    suspend operator fun invoke(): T
}
