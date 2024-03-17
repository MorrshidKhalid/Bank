package org.example.ui

import org.example.lib.Util.Companion.title

open class Screen {

    protected companion object {

        fun DrawScreenHeader(mainTitle: String) {
            title(mainTitle)
        }
    }
}