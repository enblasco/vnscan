package es.verdnatura.vnsplits.presentation.di

/**
 * Created by Enrique Blasco Blanquer on 21/2/17.
 */
interface HasComponent<C> {
    fun getSubComponent(): C
}