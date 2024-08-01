package  com.android.abhay.jetpack.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.abhay.jetpack.bases.OnEventTriggerListener
import com.android.abhay.jetpack.bases.ResponseType
import  com.android.abhay.jetpack.repo.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeVM @Inject constructor(
    private val repo: RecipeRepo
) : ViewModel() {


    fun apiGetData(listener: OnEventTriggerListener) {
        viewModelScope.launch {
            try {
                repo.apiGetData(listener)
            } catch (e: Exception) {
                listener.onErrorMessage(ResponseType.RESPONSE_FAILED, e.message)
            }
        }
    }

}