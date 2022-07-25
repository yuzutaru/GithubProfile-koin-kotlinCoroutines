package com.yuzu.githubprofile.injection

import com.yuzu.githubprofile.injection.module.*

/**
 * Created by Yustar Pramudana on 06/07/22.
 */

val appModule = listOf(networkModule, apiModule, dbModule, repositoryModule, viewModelModule)