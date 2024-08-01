package  com.android.abhay.jetpack.bases

import android.content.Intent

interface BluePrint {
    interface OnEventTriggers {
        fun onApiSuccess(any: Any?)

        fun onErrorMessage(any: Any, errorMessage: String?)

        fun getEvent(any: Any)
        fun getEvent(any: Any, variable: Any)
        fun getEvent(any: Any, actionType: Any, variable: Any)
        fun getEvent(any: Any, actionType: Any, variable: Any, view: Any)
    }

    interface OfBaseView {
        fun initializeView()
        fun connectionUpdate(hasConnection: Boolean)
        fun initializeListener()
        fun initializeData()
        fun closeEverything()
        fun initializePicker()
        fun initializeViewModel()
        fun initializeRecyclerView()
        fun initializeFragView()
        fun initializeEmptyView(isEmpty: Boolean)
        fun initializeTabView()
        fun onResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}