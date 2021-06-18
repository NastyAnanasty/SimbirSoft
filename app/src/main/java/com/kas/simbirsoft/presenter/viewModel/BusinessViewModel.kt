package com.kas.simbirsoft.presenter.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kas.simbirsoft.di.MainApp
import com.kas.simbirsoft.extensions.getCalendar
import com.kas.simbirsoft.extensions.isSameDate
import com.kas.simbirsoft.model.BusinessData
import com.kas.simbirsoft.model.BusinessRepository
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class BusinessViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: BusinessRepository

    init {
        (application as MainApp).appComponent.inject(this)
    }

    val date: MutableLiveData<Calendar> = MutableLiveData()

    private val _list: MutableLiveData<List<BusinessData>> = MutableLiveData()
    val list: LiveData<Pair<Calendar, List<BusinessData>>> =
        Transformations.switchMap(date) { date ->
            _list.map { list -> list.filter { it.dateStart.getCalendar().isSameDate(date) } }.map {
                Pair(date, it)
            }
        }

    init {
        getBusiness()
    }

    private fun getBusiness() {
        viewModelScope.launch {
            val result = repository.getBusiness()
            Log.e("TT", result.toString())
            _list.postValue(result)
        }
    }
}