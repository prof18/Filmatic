package com.prof18.filmatic.core.error

import androidx.annotation.StringRes

data class ErrorData(
    @StringRes val messageStringResID: Int,
    @StringRes val buttonTextResId: Int,
)
