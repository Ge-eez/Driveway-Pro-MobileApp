package android.aait.driveway_pro.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is history Fragment. We wanted to add history of parking but we ran out of time"
    }
    val text: LiveData<String> = _text
}