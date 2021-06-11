package com.sirius.travelpass.ui.activities.main




import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseActivityModel
import javax.inject.Inject

class MainActivityModel @Inject constructor(
    resourceProvider: ResourcesProvider

) : BaseActivityModel(resourceProvider) {

    override fun onViewCreated() {
        super.onViewCreated()

    }

}