package com.android.abhay.jetpack.bases

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.android.abhay.utils.Utils

open class BaseFragment : Fragment(), BluePrint.OfBaseView {
    /**
     * [uploading] variable hold value for Api Transaction. User [setUploadStatus] and [isUploading]
     * True if api transaction pending else false
     *
     * @author Abhay gupta
     */
    private var uploading: Boolean = false

    /**
     * [canLoadMore] provide data is more to load or not. Use [setCanLoadMore] and [canLoadMore]
     *
     * @author Abhay gupta
     */
    private var canLoadMore: Boolean = false

    private var changed: Boolean = false

    private var requestCode: Int = 0

    private lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var utils: Utils

    /**
     * [launcher] is the new way to launch activities and call backs
     */
    fun launcher(requestCode: Int? = 0): ActivityResultLauncher<Intent> {
        this.requestCode = requestCode!!
        return launcher
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils = Utils(requireActivity())
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                onResult(requestCode, result.resultCode, result.data)
            }
    }

    /**
     * Only view initialization
     *
     * @author Abhay gupta
     */
    override fun initializeView() {

    }

    override fun connectionUpdate(hasConnection: Boolean) {
    }

    /**
     * All kind of listeners call here
     *
     * @author Abhay gupta
     */

    override fun initializeListener() {
    }

    /**
     * Any kind of Pickers implementation
     *
     * @author Abhay gupta
     */
    override fun initializePicker() {

    }

    /**
     * Data going to be fill in UI comes here
     *
     * @author Abhay gupta
     */
    override fun initializeData() {

    }

    /**
     * User of [androidx.lifecycle.ViewModel] or if need initialization comes their
     *
     * @author Abhay gupta
     */
    override fun initializeViewModel() {

    }

    /**
     * In case of [com.google.android.material.tabs.TabLayout] and [androidx.viewpager.widget.ViewPager]
     *
     * @author Abhay gupta
     */
    override fun initializeTabView() {

    }

    /**
     * User for [androidx.recyclerview.widget.RecyclerView.Recycler] Implementation
     *
     * @author Abhay gupta
     */
    override fun initializeRecyclerView() {

    }

    override fun initializeFragView() {
        TODO("Not yet implemented")
    }

    /**
     * In case of [androidx.recyclerview.widget.RecyclerView.Recycler] we have function to maintain no data information view
     *
     * @author Abhay gupta
     */
    override fun initializeEmptyView(isEmpty: Boolean) {

    }

    override fun onDestroy() {
        super.onDestroy()
        closeEverything()
    }

    /**
     * Function to call from [onDestroy] and on backpress to cancel threads or task running
     *
     * @author Abhay gupta
     */
    override fun closeEverything() {

    }

    /**
     * Update status of Api transaction by this
     * @author Abhay gupta
     */
    fun setUploadStatus(isUploading: Boolean) {
        this.uploading = isUploading
    }

    /**
     * Get status of api transaction by this
     *
     * @author Abhay gupta
     */
    fun isUploading(): Boolean {
        return uploading
    }

    fun hasConnection(): Boolean {
        return utils.isNetworkAvailable(requireContext())
    }

    /**
     * Check something is changed or not
     *
     * @author Abhay gupta
     */
    fun isChanged(): Boolean {
        return changed
    }

    /**
     * Update load more availability by this
     * @author Abhay gupta
     */
    fun setChanged(changed: Boolean) {
        this.changed = changed
    }

    /**
     * Update load more availability by this
     * @author Abhay gupta
     */
    fun setCanLoadMore(canLoadMore: Boolean) {
        this.canLoadMore = canLoadMore
    }

    /**
     * Get status of load more availability by this
     * @author Abhay gupta
     */
    fun canLoadMore(): Boolean {
        return canLoadMore
    }

    /**
     * Result callback for an Activity Launcher
     * [requestCode] : used to check used for
     * [resultCode] : [Activity.RESULT_OK], [Activity.RESULT_CANCELED]
     * [data] : [Intent] in bundle data
     */
    override fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }


}
