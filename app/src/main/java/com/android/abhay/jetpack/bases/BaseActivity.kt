package  com.android.abhay.jetpack.bases

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.abhay.utils.Utils

var isBaseFirstTime = true

abstract class BaseActivity : AppCompatActivity(), BluePrint.OfBaseView {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var requestCode: Int = 0
    private var hasConnection: Boolean = false
    private var uploading: Boolean = false
    private var canLoadMore: Boolean = false
    lateinit var utils: Utils

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    private val defaultUEH by lazy {
        Thread.getDefaultUncaughtExceptionHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils = Utils(this)
        utils.doStatusBarColorPrimary()
        hasConnection = utils.isNetworkAvailable(this)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                onResult(requestCode, result.resultCode, result.data)
            }
    }

    fun launcher(requestCode: Int? = 0): ActivityResultLauncher<Intent> {
        this.requestCode = requestCode!!
        return launcher
    }


    /**
     * [connectionUpdate] override this method to maintain view bases of network configuration
     *
     * @author Abhay gupta
     */
    override fun connectionUpdate(hasConnection: Boolean) {
        this.hasConnection = hasConnection
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

    override fun onStart() {
        super.onStart()
        setNetworkIntent()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun setNetworkIntent() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    /**
     * Broadcaster for monitoring and status update on network changes
     * @author Abhay gupta
     */
    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            connectionUpdate(utils.isNetworkAvailable(this@BaseActivity))
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onPause() {
        isBaseFirstTime = false
        super.onPause()
    }

    override fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun initializeData() {

    }

    override fun closeEverything() {
    }

    override fun initializePicker() {

    }

    override fun initializeRecyclerView() {

    }

    override fun initializeTabView() {

    }

    override fun initializeView() {

    }

    override fun initializeViewModel() {

    }

    override fun initializeListener() {

    }

    override fun initializeFragView() {

    }

    override fun initializeEmptyView(isEmpty: Boolean) {
    }

    private fun clearStorage() {

    }

}