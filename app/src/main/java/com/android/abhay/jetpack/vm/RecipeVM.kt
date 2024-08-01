package  com.android.abhay.jetpack.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.abhay.jetpack.bases.OnEventTriggerListener
import  com.android.abhay.jetpack.repo.RecipeRepo
import kotlinx.coroutines.launch

class RecipeVM : ViewModel() {
    private var repo: RecipeRepo = RecipeRepo()

    fun apiGetData(listener: OnEventTriggerListener) {
        viewModelScope.launch {
            repo.apiGetData(listener)
        }
    }

}