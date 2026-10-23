package com.arnix.typey.ui.main

import com.arnix.typey.common.base.BaseViewModel
import com.arnix.typey.repository.autotext.AutoTextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AutoTextRepository
): BaseViewModel() {


}