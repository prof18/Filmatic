package com.prof18.filmatic.libraries.testshared.fakes

import com.prof18.filmatic.core.net.ConnectivityChecker
import javax.inject.Inject

class FakeConnectivityCheckReturnSuccess @Inject constructor() : ConnectivityChecker {
    override fun hasInternetAccess(): Boolean = true
}

class FakeConnectivityCheckReturnNotSuccess : ConnectivityChecker {
    override fun hasInternetAccess(): Boolean = false
}
