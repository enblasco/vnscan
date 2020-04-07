package es.verdnatura.vnsplits.data.exceptions

import android.content.Context
import es.verdnatura.vnsplits.data.R

/**
 * Created by Enrique Blasco Blanquer on 1/3/17.
 */
class NotFoundException(context: Context) : RuntimeException(context.resources.getString(R.string.error_404))