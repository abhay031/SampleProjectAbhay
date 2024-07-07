package  com.newsbytes.abhay.jetpack.vm

import androidx.lifecycle.ViewModel
import com.newsbytes.abhay.jetpack.bases.OnEventTriggerListener
import  com.newsbytes.abhay.jetpack.repo.RecipeRepo

class RecipeVM : ViewModel() {
    private var repo: RecipeRepo = RecipeRepo()

    fun apiGetData(listener: OnEventTriggerListener) {
        repo.apiGetData(listener)
    }
}