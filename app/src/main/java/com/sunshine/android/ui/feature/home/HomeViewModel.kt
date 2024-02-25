package com.sunshine.android.ui.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.data.network.dto.user.asUserModel
import com.sunshine.android.domain.model.JournalModel

import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.UserModel
import com.sunshine.android.domain.usecase.GetAlbumUseCase
import com.sunshine.android.domain.usecase.GetHomeUseCase
import com.sunshine.android.domain.usecase.GetJournalUseCase
import com.sunshine.android.domain.usecase.GetQuestListUseCase
import com.sunshine.android.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val getQuestListUseCase: GetQuestListUseCase,
    private val getAlbumUseCase: GetAlbumUseCase,
    private val getJournalUseCase: GetJournalUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _tutorialIterator = tutorialDialogResources.iterator()

    private val _uiState = MutableStateFlow(
        HomeUiState(currentTutorial = _tutorialIterator.next())
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetch()
    }

    fun fetch() {
        fetchHome()
        fetchQuest()
        fetchAlbum()
        fetchJournal()
    }

    private fun fetchHome() {
        viewModelScope.launch {
            getHomeUseCase().onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = false) }
            }.catch {
                _uiState.update { it.copy(error = true) }
            }.collectLatest { response ->
                _uiState.update {
                    it.copy(
                        user = response.asUserModel(),
                        daysLeft = response.leftDay,
                        showQuestMark = response.uncompletedQuestSize > 0,
                    )
                }
                _uiState.update {
                    it.copy(
                        showTutorial = it.user!!.exp == 0 && it.user.level == 1 && !it.tutorialFinished,
                    )
                }
            }
        }
    }

    private fun fetchQuest() {
        viewModelScope.launch {
            getQuestListUseCase().onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = false) }
            }.catch { e ->
                _uiState.update { it.copy(error = true) }
                Log.e("home", "fetchQuest: ", e)
            }.collectLatest { response ->
                _uiState.update { it.copy(quests = response) }
            }
        }
    }

    private fun fetchJournal() {
        viewModelScope.launch {
            getJournalUseCase().onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = false) }
            }.catch { e ->
                _uiState.update { it.copy(error = true) }
                Log.e("home", "fetchJournal: ", e)
            }.collectLatest { response ->
                _uiState.update { it.copy(journal = response) }
            }
        }
    }

    private fun fetchAlbum() {
        viewModelScope.launch {
            getAlbumUseCase().onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = false) }
            }.catch { e ->
                Log.e("album", "fetchJournal: ", e)
                _uiState.update { it.copy(error = true) }
            }.collectLatest { response ->
                _uiState.update { it.copy(album = response) }
            }
        }
    }

    fun nextTutorial() {
        if (!_tutorialIterator.hasNext()) {
            _uiState.update {
                it.copy(showTutorial = false, tutorialFinished = true)
            }
            return
        }
        _uiState.update {
            it.copy(currentTutorial = _tutorialIterator.next())
        }
    }

    fun logout(onFinish: () -> Unit) {
        viewModelScope.launch {
            logoutUseCase()
            onFinish()
        }
    }
}

data class HomeUiState(
    val currentTutorial: TutorialDialog,
    val showTutorial: Boolean = false,
    val tutorialFinished: Boolean = false,
    val showQuestMark: Boolean = false,
    val loading: Boolean = true,
    val error: Boolean = false,
    val user: UserModel? = null,
    val daysLeft: Int = 0,
    val quests: List<List<QuestModel>> = listOf(listOf(), listOf(), listOf()),
    val journal: List<JournalModel> = listOf(),
    val album: List<String> = listOf(),
    val logout: Boolean = false
)