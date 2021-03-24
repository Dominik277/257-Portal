package Portal.viewModel

import Portal.database.Portal257Database
import Portal.database.table.SportTable
import Portal.database.table.ZabavaTable
import Portal.repository.SportRepository
import Portal.repository.ZabavaRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZabavaViewModel @Inject constructor(
    val zabavaRepository: ZabavaRepository
) : ViewModel() {

    val readAllDataZabava = zabavaRepository.getAllDataZabava()

    fun addZabava(zabavaTable: ZabavaTable) {
        viewModelScope.launch(Dispatchers.IO) {
            zabavaRepository.addZabava(zabavaTable)
        }
    }

    fun updateZabava(zabavaTable: ZabavaTable) {
        viewModelScope.launch(Dispatchers.IO) {
            zabavaRepository.updateZabava(zabavaTable)
        }
    }

    fun deleteZabava(zabavaTable: ZabavaTable) {
        viewModelScope.launch(Dispatchers.IO) {
            zabavaRepository.deleteZabava(zabavaTable)
        }
    }

    fun deleteAllZabava() {
        viewModelScope.launch(Dispatchers.IO) {
            zabavaRepository.deleteAllZabava()
        }
    }

}